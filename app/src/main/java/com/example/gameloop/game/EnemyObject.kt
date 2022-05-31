package com.example.gameloop.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.gameloop.R

class EnemyObject(val context: Context, val positionX: Int, var positionY: Int, val image: Bitmap, val screenHeight: Int) {

    private val INITAL_SPAWN_PER_MINUTES = 70
    var spawnPerMinute = INITAL_SPAWN_PER_MINUTES
    var spawnPerSeconds:Double = spawnPerMinute/60.0
    var updatesPerSpawn: Double = GameLoop.MAX_UPS/spawnPerSeconds
    var updatesUntilNextSpawn: Double = updatesPerSpawn


    fun draw(canvas: Canvas, positionX: Int, positionY: Int, image: Bitmap, paint: Paint){
        canvas.drawBitmap(image, positionX.toFloat(),positionY.toFloat(),paint)
    }

    fun update(){
        positionY++
    }

    fun isPositionYOutOfView(): Boolean{
        return positionY > screenHeight + 100
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

}