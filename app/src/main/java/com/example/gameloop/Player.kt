package com.example.gameloop

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class Player(context: Context) {

    private val image: Drawable? = ContextCompat.getDrawable(context, R.mipmap.jetpack_logo)
    private var image2 = BitmapFactory.decodeResource(context.resources,R.mipmap.jetpack_logo_player)
    private var positionX: Double = 0.0
    private var positionY: Double = 0.0
    var paint: Paint = Paint()

    fun draw(canvas: Canvas, playerX: Int, playerY: Int){
        image?.setBounds(2,2,2,2)
        image?.draw(canvas)

        //canvas.drawBitmap(image2, null , Rect(playerX,playerY,1000,1000),paint)
        canvas.drawBitmap(image2, playerX.toFloat()-200,playerY.toFloat()-400,paint)
    }

    fun update(){

    }

    fun setPosition(positionX: Double,positionY:Double){
        this.positionX = positionX
        this.positionY = positionY
    }
}