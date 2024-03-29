package com.example.androidjetpack.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.roundToLong

class Score(private var positionX: Int, private var positionY: Int) {

    var paint: Paint = Paint()

    init {
        paint.color = Color.WHITE
        paint.textSize = 100f
    }

    companion object{
        var scoreCounter = 0.0
    }

    fun draw(canvas: Canvas, positionX: Int, positionY: Int){
        canvas.drawText(scoreCounter.roundToLong().toString(),positionX.toFloat(),positionY.toFloat(),paint)
    }

    fun update(){
        scoreCounter += 0.04
    }

}