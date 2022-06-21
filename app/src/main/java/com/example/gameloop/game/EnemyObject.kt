package com.example.gameloop.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.gameloop.R

private const val INITIAL_SPAWN_PER_MINUTES = 70

class EnemyObject(val context: Context, var positionY: Int, val image: Bitmap, private val screenHeight: Int, private val screenWidth: Int) {

    var positionX: Int = 0

    //TODO move vars into the companion object
    var spawnPerMinute = INITIAL_SPAWN_PER_MINUTES
    var spawnPerSeconds:Double = spawnPerMinute/60.0
    var updatesPerSpawn: Double = GameLoop.MAX_UPS/spawnPerSeconds
    var updatesUntilNextSpawn: Double = updatesPerSpawn

    init {
        positionX = generateARandomXPosition(screenWidth)
    }

    fun draw(canvas: Canvas, positionX: Int, positionY: Int, image: Bitmap, paint: Paint){
        canvas.drawBitmap(image, positionX.toFloat(),positionY.toFloat(),paint)
    }

    fun update(){
        positionY++
    }

    fun isPositionYOutOfView(): Boolean{
        return positionY > screenHeight
    }

    fun readyToSpawn(): Boolean{
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += updatesPerSpawn
            return true
        } else {
            updatesUntilNextSpawn --
            return false
        }
    }

    fun generateImageRandomly(context: Context): Bitmap {
        val random = (1..5).random()
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

    companion object{
        fun generateARandomXPosition(screenWidth: Int): Int {
            return (70..screenWidth - 100).random()
        }
    }


}