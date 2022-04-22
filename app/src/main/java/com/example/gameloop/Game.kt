package com.example.gameloop

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    var paint: Paint = Paint()
    var surfaceView = holder
    var gameLoop = GameLoop(this, surfaceView)

    init {
        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.RED

        surfaceView.addCallback(this)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        var backgroundColor = ContextCompat.getColor(context,R.color.black)
        canvas?.drawColor(backgroundColor)
        drawUPS(canvas)
        drawFPS(canvas)
    }

    fun drawUPS(canvas: Canvas?){
        var averageUPS = gameLoop.getAverageUPS().toString()
        val color = ContextCompat.getColor(context, R.color.red)
        paint.color = color
        paint.textSize = 70f
        canvas?.drawText("UPS: $averageUPS",200f,250f, paint)
    }

    fun drawFPS(canvas: Canvas?){
        var averageFPS = gameLoop.getAverageFPS().toString()
        val color = ContextCompat.getColor(context, R.color.red)
        paint.color = color
        paint.textSize = 70f
        canvas?.drawText("FPS: $averageFPS",200f,350f, paint)
    }

    fun update(){

    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
    }
}