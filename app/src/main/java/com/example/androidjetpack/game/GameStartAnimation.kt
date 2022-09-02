package com.example.androidjetpack.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.androidjetpack.R

class GameStartAnimation() {

    private val paint = Paint()

    fun draw(canvas: Canvas, screenWidth: Int, screenHeight: Int, context: Context) {
        if (GameLoop.runningTime < 1000) {
            generateAnimationNumberThree(canvas, screenWidth, screenHeight, context)
        }
        if (GameLoop.runningTime in 1001..2000) {
            generateAnimationNumberTwo(canvas, screenWidth, screenHeight, context)
        }
        if (GameLoop.runningTime in 2001..3000) {
            generateAnimationNumberOne(canvas, screenWidth, screenHeight, context)
        }
        if (GameLoop.runningTime in 3000..4000) {
            generateAnimationGo(canvas, screenWidth, screenHeight, context)
        }
        if (GameLoop.runningTime > 4000){
            return
        }
    }


    fun update() {
    }

    private fun generateAnimationNumberThree(canvas: Canvas, screenWidth: Int, screenHeight: Int, context: Context) {
        //paint.alpha = (1 / (animationThreadRunningTime / 3000).toDouble()).toInt()
//        canvas.setBitmap(
//            Bitmap.createScaledBitmap(
//                generateImageNumberThree(context),
//                (animationThreadRunningTime / 5).toInt(),
//                (animationThreadRunningTime / 5).toInt(),
//                false
//            )
//        )
        canvas.drawBitmap(
            generateImageNumberThree(context),
            (screenWidth/2.4).toFloat(),
            (screenHeight/4.4).toFloat(),
            paint
        )
    }

    private fun generateImageNumberThree(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.mipmap.three_image_small)
    }

    private fun generateAnimationNumberTwo(canvas: Canvas, screenWidth: Int, screenHeight: Int, context: Context) {
//        canvas.setBitmap(
//            Bitmap.createScaledBitmap(
//                generateImageNumberTwo(context),
//                (animationThreadRunningTime / 5).toInt(),
//                (animationThreadRunningTime / 5).toInt(),
//                false
//            )
//        )
        canvas.drawBitmap(
            generateImageNumberTwo(context),
            (screenWidth/2.4).toFloat(),
            (screenHeight/4.4).toFloat(),
            paint
        )
    }

    private fun generateImageNumberTwo(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.mipmap.two_image_small)
    }

    private fun generateAnimationNumberOne(canvas: Canvas, screenWidth: Int, screenHeight: Int, context: Context) {
//        canvas.setBitmap(
//            Bitmap.createScaledBitmap(
//                generateImageNumberOne(context),
//                (animationThreadRunningTime / 5).toInt(),
//                (animationThreadRunningTime / 5).toInt(),
//                false
//            )
//        )
        canvas.drawBitmap(
            generateImageNumberOne(context),
            (screenWidth/2.4).toFloat(),
            (screenHeight/4.4).toFloat(),
            paint
        )
    }

    private fun generateImageNumberOne(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.mipmap.one_image_small)
    }

    private fun generateAnimationGo(canvas: Canvas, screenWidth: Int, screenHeight: Int, context: Context) {
        canvas.drawBitmap(
            generateImageGo(context),
            screenWidth.toFloat() / 5,
            screenHeight.toFloat() / 7,
            paint
        )
    }

    private fun generateImageGo(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.mipmap.go_small)
    }

}


