package com.example.gameloop

import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder
import java.lang.Exception
import java.lang.IllegalArgumentException

class GameLoop(game: Game, surfaceHolder: SurfaceHolder): Thread() {

    private var isRunning = false
    private lateinit var canvas: Canvas
    private var surfaceHolder = surfaceHolder
    private var game = game
    private var averageUPS = 0.0
    private var averageFPS = 0.0

    private val MAX_UPS = 60.0
    private val UPS_PERIOD = 1E+3/MAX_UPS


    fun getAverageUPS(): Double{
        return averageUPS
    }

    fun getAverageFPS(): Double{
        return averageFPS
    }

    fun startLoop(){
        isRunning = true
        start()
    }
    override fun run() {

        // declare time and cycle count variable
        var updateCount = 0
        var frameCount = 0

        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long

        // Game Loop
        startTime = System.currentTimeMillis()
        while (isRunning){

            // update and render game
        try {
            canvas = surfaceHolder.lockCanvas()
            synchronized(surfaceHolder){
                game.update()
                updateCount++
                game.draw(canvas)
            }
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }finally {
            if (canvas != null){
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                    frameCount++
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

            // Pause our loop to not exceed our target UPS
            elapsedTime = System.currentTimeMillis() - startTime
            sleepTime = (updateCount*UPS_PERIOD - elapsedTime).toLong()
            if(sleepTime > 0){
                try {
                    sleep(sleepTime)
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
            // Skip frames to keep up with the target UPS
            while (sleepTime < 0 && updateCount < MAX_UPS -1){
                game.update()
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount*UPS_PERIOD - elapsedTime).toLong()
            }
            // Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000){
                averageUPS = (updateCount / (elapsedTime/1000)).toDouble()
                Log.d("averageUPS", averageUPS.toString())
                averageFPS = frameCount / (elapsedTime/10).toDouble()
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
        }
    }
}