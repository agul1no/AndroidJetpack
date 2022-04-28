package com.example.gameloop.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.gameloop.R

class Player(context: Context) {

    private var imageAndroidPlayer = BitmapFactory.decodeResource(context.resources, R.mipmap.jetpack_logo_player)
    var paint: Paint = Paint()

    fun draw(canvas: Canvas, playerX: Int, playerY: Int){
        canvas.drawBitmap(imageAndroidPlayer, playerX.toFloat()-200,playerY.toFloat()-400,paint)
    }

    fun update(){

    }

    fun generateImageRandomly(context: Context): Bitmap {
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


}