package com.example.gameloop.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.gameloop.R

class Life(context: Context, private var playerLives: Int) {

    var paint: Paint = Paint()

    fun draw(canvas: Canvas, lifeX: Int, lifeY: Int, playerLives: Int, context: Context){
        if (playerLives == 3){
            threeLives(canvas, context, lifeX, lifeY)
        }
        if (playerLives == 2){
            twoLives(canvas, context, lifeX, lifeY)
        }
        if (playerLives == 1){
            oneLife(canvas, context, lifeX, lifeY)
        }
        if (playerLives == 0){
            zeroLives(canvas, context, lifeX, lifeY)
        }

    }

    fun update(){

    }

    fun threeLives(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-50,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-150,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-250,lifeY.toFloat(),paint)
    }

    fun twoLives(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-50,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-150,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-250,lifeY.toFloat(),paint)
    }

    fun oneLife(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-50,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-150,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-250,lifeY.toFloat(),paint)
    }

    fun zeroLives(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-50,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-150,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-250,lifeY.toFloat(),paint)
    }

    fun generateImageHealthyHeart(context: Context): Bitmap{
        var bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.heart_emoji_small)
        return bitmap
    }

    fun generateImageBrokenHeart(context: Context): Bitmap{
        var bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.broken_heart_emoji_small)
        return bitmap
    }
}