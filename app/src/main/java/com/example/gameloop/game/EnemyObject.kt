package com.example.gameloop.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build.VERSION_CODES.M
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import kotlinx.coroutines.NonCancellable.start
import java.util.*
import java.util.logging.Handler
import javax.xml.transform.dom.DOMLocator
import kotlin.collections.RandomAccess
import kotlin.concurrent.schedule

class EnemyObject(context: Context, val positionX: Int, var positionY: Int, val image: Bitmap) {

    private val SPAWN_PER_MINUTES = 100
    private val SPAWN_PER_SECONDS:Double = SPAWN_PER_MINUTES/60.0
    private val UPDATES_PER_SPAWN: Double = GameLoop.MAX_UPS/SPAWN_PER_SECONDS
    private var updatesUntilNextSpawn: Double = UPDATES_PER_SPAWN


    fun draw(canvas: Canvas, positionX: Int, positionY: Int, image: Bitmap, paint: Paint){
        canvas.drawBitmap(image, positionX.toFloat(),positionY.toFloat(),paint)
    }

    fun update(){
        positionY++
    }

    fun isPositionYOutOfView(): Boolean{
        return positionY > 2600
    }

    fun readyToSpawn(): Boolean{
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN
            return true
        } else {
            updatesUntilNextSpawn --
            return false
        }
    }

}