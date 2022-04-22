package com.example.gameloop

import android.graphics.Canvas
import android.view.SurfaceHolder
import java.lang.IllegalArgumentException

class GameLoop(game: Game, surfaceHolder: SurfaceHolder): Thread() {

    private var isRunning = false
    private lateinit var canvas: Canvas
    private var surfaceHolder = surfaceHolder
    private var game = game


    fun getAverageUPS(): Double{
        var averageUPS = 0.0
        return averageUPS
    }

    fun getAverageFPS(): Double{
        var averageFPS = 0.0
        return averageFPS
    }

    fun startLoop(){
        isRunning = true
        start()
    }
    override fun run() {
        // Game Loop
        while (isRunning){

            // update and render game
        try {
            canvas = surfaceHolder.lockCanvas()
            game.update()
            game.draw(canvas)
            surfaceHolder.unlockCanvasAndPost(canvas)
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }

            // Pause our loop to not exceed our target UPS

            // Skip frames to keep up with the target UPS

            // Calculate average UPS and FPS

        }
    }
}