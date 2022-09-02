package com.example.androidjetpack.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.androidjetpack.R

class Life() {

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
        if (playerLives <= 0){
            zeroLives(canvas, context, lifeX, lifeY)
        }

    }

    fun update(){
        // left empty on purpose
    }

    private fun threeLives(canvas: Canvas, context: Context, lifeX: Int, lifeY: Int){
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-100,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-200,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-300,lifeY.toFloat(),paint)
    }

    private fun twoLives(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-100,lifeY.toFloat(),paint)
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-200,lifeY.toFloat(),paint)
        //canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-300,lifeY.toFloat(),paint)
    }

    private fun oneLife(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        canvas.drawBitmap(generateImageHealthyHeart(context), lifeX.toFloat()-100,lifeY.toFloat(),paint)
        //canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-200,lifeY.toFloat(),paint)
        //canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-300,lifeY.toFloat(),paint)
    }

    private fun zeroLives(canvas: Canvas,context: Context,lifeX: Int,lifeY: Int){
        //canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-100,lifeY.toFloat(),paint)
        //canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-200,lifeY.toFloat(),paint)
        //canvas.drawBitmap(generateImageBrokenHeart(context), lifeX.toFloat()-300,lifeY.toFloat(),paint)
    }

    private fun generateImageHealthyHeart(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.mipmap.heart_emoji_small)
    }

    private fun generateImageBrokenHeart(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.mipmap.broken_heart_emoji_small)
    }
}