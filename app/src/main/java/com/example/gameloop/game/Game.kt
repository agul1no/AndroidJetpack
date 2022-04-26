package com.example.gameloop.game

import android.content.Context
import android.graphics.*
import android.util.DisplayMetrics
import java.util.function.Predicate
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.example.gameloop.R

class Game(context: Context, screenWith: Int, screenHeight: Int) : SurfaceView(context), SurfaceHolder.Callback{

    private var paint: Paint = Paint()
    private var surfaceView = holder
    private var gameLoop = GameLoop(this, surfaceView)
    private var player = Player(context)
    private var enemyObject = EnemyObject(context,-200,0, BitmapFactory.decodeResource(context.resources, R.mipmap.cake_object_small))
    private var listOfEnemyObject = mutableListOf<EnemyObject>()
    //private var itemsToRemove = mutableListOf<EnemyObject>()
    val metrics = DisplayMetrics()

    var playerXPosition = screenWith/2  //580
    var playerYPosition = screenHeight*4/5  //1800
    private val OBJECT_VELOCITY = 20

    init {
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.RED

        surfaceView.addCallback(this)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //return super.onTouchEvent(event)

        playerXPosition = event?.x!!.toInt()
        playerYPosition = event.y.toInt()
        invalidate()
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        var backgroundColor = ContextCompat.getColor(context, R.color.black)
        canvas.drawColor(backgroundColor)
        drawUPS(canvas)
        drawFPS(canvas)

        player.draw(canvas, playerXPosition, playerYPosition)

        //enemyObject.draw(canvas)

        for (item in listOfEnemyObject){
            item.positionY = item.positionY + OBJECT_VELOCITY
            item.draw(canvas,item.positionX,item.positionY,item.image,paint)
        }
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

    fun update(){
        player.update()
        //enemyObject.update()

        if(enemyObject.readyToSpawn()){
            var newEnemy = EnemyObject(context,generateARandomXPosition(),0,generateImageRandomly())
            listOfEnemyObject.add(newEnemy)
        }
        for (item in listOfEnemyObject) {
            enemyObject.update()
        }
        var enemyObjectIterator = listOfEnemyObject.iterator()
        for (i in enemyObjectIterator){
            if (i.isPositionYOutOfView()) {enemyObjectIterator.remove()}
            if ((i.positionY+100 > playerYPosition-350 && i.positionY+100<playerYPosition) && (i.positionX > playerXPosition-250 && i.positionX < playerXPosition+120)){
                enemyObjectIterator.remove()
            }
        }
    }

    fun generateImageRandomly(): Bitmap{
        var random = (1..5).random()
        var bitmap: Bitmap
        when(random){
            1 -> bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.cake_object_small)
            2 -> bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.candy_emoji_small)
            3 -> bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.cholate_emoji_small)
            4 -> bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.cookie_emoji_small)
            5 -> bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.lollipop_emoji_small)
            else -> bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.cake_object_small)
        }
        return bitmap
    }

    fun generateARandomXPosition(): Int{  /** display.width has to be changed but it work for now **/
        var randomXPositionEnemy = (60..display.width-60).random()
        return randomXPositionEnemy
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
    }

}