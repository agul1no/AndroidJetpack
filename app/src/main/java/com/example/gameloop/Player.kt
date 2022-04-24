package com.example.gameloop

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Player(context: Context) {

    private var imageAndroidPlayer = BitmapFactory.decodeResource(context.resources,R.mipmap.jetpack_logo_player)
    var paint: Paint = Paint()

    fun draw(canvas: Canvas, playerX: Int, playerY: Int){
        canvas.drawBitmap(imageAndroidPlayer, playerX.toFloat()-200,playerY.toFloat()-400,paint)
    }

    fun update(){

    }


}