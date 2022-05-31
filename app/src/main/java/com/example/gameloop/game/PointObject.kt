package com.example.gameloop.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.gameloop.R

class PointObject(val context: Context, val name: String ,val positionX: Int, var positionY: Int, val image: Bitmap, val screenHeight: Int, val screenWidth: Int) {

    private val INITAL_SPAWN_PER_MINUTES = 10
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

    fun generatePointObjectRandomly(): PointObject{
        val random = (1..3).random()
        var pointObject: PointObject
        when(random){
            1 -> pointObject = PointObject(context, "ten",generateARandomXPosition(), 0, BitmapFactory.decodeResource(context.resources,R.mipmap.ten_small),screenHeight,screenWidth)
            2 -> pointObject = PointObject(context, "twenty",generateARandomXPosition(), 0, BitmapFactory.decodeResource(context.resources,R.mipmap.twenty_small),screenHeight,screenWidth)
            3 -> pointObject = PointObject(context, "thirty",generateARandomXPosition(), 0, BitmapFactory.decodeResource(context.resources,R.mipmap.thirty_small),screenHeight,screenWidth)
            else -> pointObject = PointObject(context, "ten",generateARandomXPosition(), 0, BitmapFactory.decodeResource(context.resources,R.mipmap.ten_small),screenHeight,screenWidth)
        }
        return pointObject
    }

    private fun generateARandomXPosition(): Int {
        return (70..screenWidth - 100).random()
    }
}