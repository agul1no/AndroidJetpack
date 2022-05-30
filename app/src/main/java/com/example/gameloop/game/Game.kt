package com.example.gameloop.game

import android.content.Context
import android.graphics.*
import android.os.*
import android.util.DisplayMetrics
import java.util.function.Predicate
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.fragments.GameFragment
import kotlin.math.abs

class Game(context: Context, private var screenWith: Int, private var screenHeight: Int, vibrator: Vibrator?, private var fragment: GameFragment) : SurfaceView(context), SurfaceHolder.Callback{

    private val PLAYER_INITIAL_LIVES = 3
    var playerLives = PLAYER_INITIAL_LIVES

    private val OBJECT_INITIAL_VELOCITY = 16
    var enemyObjectVelocity = OBJECT_INITIAL_VELOCITY
    private var enemyObject = EnemyObject(context,-200,0, BitmapFactory.decodeResource(context.resources, R.mipmap.cake_object_small),screenHeight) // creating an enemyObject for accessing the methods of the enemy class
    private var listOfEnemyObject = mutableListOf<EnemyObject>()

    private var paint: Paint = Paint()
    private var surfaceView = holder
    private var gameLoop = GameLoop(this, surfaceView,vibrator,playerLives, enemyObject)

    private var player = Player(context)
    var playerXPosition = screenWith / 2
    var playerYPosition = screenHeight* 4/5

    private var life = Life(context, playerLives)

    private var lifeObject = LifeObject(context,-200,0,LifeObject.generateImageHealthyHeart(context),screenHeight)
    private var listOfLifeObject = mutableListOf<LifeObject>()

    private var score = Score(100,100)


    init {
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.RED

        surfaceView.addCallback(this)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //return super.onTouchEvent(event)

        if(abs(playerXPosition-event?.x!!.toInt()) < 200 && abs(playerYPosition-event.y.toInt()) < 200){
            playerXPosition = event.x.toInt()
            playerYPosition = event.y.toInt()
        }
        if (playerXPosition < 150) playerXPosition = 150
        if (playerXPosition > screenWith - 100) playerXPosition = screenWith - 100

        invalidate()
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        val backgroundColor = ContextCompat.getColor(context, R.color.black)
        canvas.drawColor(backgroundColor)
        drawUPS(canvas)
        drawFPS(canvas)

        player.draw(canvas, playerXPosition, playerYPosition)
        life.draw(canvas,screenWith, 50, playerLives, context)
        score.draw(canvas,100, 120)

        for (item in listOfEnemyObject){
            item.positionY = item.positionY + enemyObjectVelocity
            item.draw(canvas,item.positionX,item.positionY,item.image,paint)
        }

        for (item in listOfLifeObject){
            item.positionY += enemyObjectVelocity
            item.draw(canvas,item.positionX,item.positionY,item.image,paint)
        }
    }

    fun update(vibrator: Vibrator?){
        player.update()
        life.update()
        score.update()

        /** creates an enemy object in a random position along the screen and updates its position */
        if(enemyObject.readyToSpawn()){
            val newEnemy = EnemyObject(context,generateARandomXPosition(),0,enemyObject.generateImageRandomly(context),screenHeight)
            listOfEnemyObject.add(newEnemy)
        }
        for (item in listOfEnemyObject) {
            enemyObject.update()
        }
        /** creates a life object in a random position along the screen and updates its position */
        if (lifeObject.readyToSpawn()){
            val newLifeObject = LifeObject(context,generateARandomXPosition(),0,LifeObject.generateImageHealthyHeart(context),screenHeight)
            listOfLifeObject.add(newLifeObject)
        }
        for (item in listOfLifeObject){
            item.update()
        }

        /** checks if the player touches an enemy object, delete the object, update the lives and vibrates */
        val enemyObjectIterator = listOfEnemyObject.iterator()
        for (i in enemyObjectIterator){
            if (i.isPositionYOutOfView()) {enemyObjectIterator.remove()}
            if ((i.positionY+100 > playerYPosition-350 && i.positionY+100<playerYPosition) && (i.positionX > playerXPosition-250 && i.positionX < playerXPosition+120)){
                enemyObjectIterator.remove()
                playerLives--
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(50,VibrationEffect.EFFECT_TICK))
                }
            }
        }

        /** checks if the player touches a life object, delete the object, update the lives and vibrates */
        val lifeObjectIterator = listOfLifeObject.iterator()
        for (item in lifeObjectIterator){
            if (item.isPositionYOutOfView()){ lifeObjectIterator.remove() }
            if ((item.positionY+100 > playerYPosition-350 && item.positionY+100<playerYPosition) && (item.positionX > playerXPosition-250 && item.positionX < playerXPosition+120)){
                lifeObjectIterator.remove()
                if(playerLives < 3){ playerLives++ }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(50,VibrationEffect.EFFECT_TICK))
                }
            }
        }

        if(playerLives <= 0){
            pauseGame()
            fragment.moveToTheAfterGameFragment()
            return
        }
    }

    fun pauseGame(){
        gameLoop.stopLoop()
    }

    fun resumeGame(){
        gameLoop.resumeLoop()
    }

    private fun generateARandomXPosition(): Int{  /** display.width has to be changed but it work for now **/
        val randomXPosition = (60..screenWith-100).random()
        return randomXPosition
    }

    /** implemented methods from SurfaceVIew */
    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        // left empty cause don't needed
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        // left empty cause don't needed
    }

    fun drawUPS(canvas: Canvas?){
        var averageUPS = gameLoop.getAverageUPS().toString()
        val color = ContextCompat.getColor(context, R.color.red)
        paint.color = color
        paint.textSize = 50f
        canvas?.drawText("UPS: $averageUPS",100f,250f, paint)
    }

    fun drawFPS(canvas: Canvas?){
        var averageFPS = gameLoop.getAverageFPS().toString()
        val color = ContextCompat.getColor(context, R.color.red)
        paint.color = color
        paint.textSize = 50f
        canvas?.drawText("FPS: $averageFPS",100f,350f, paint)
    }

}