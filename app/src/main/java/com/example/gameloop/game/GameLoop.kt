package com.example.gameloop.game

import android.graphics.Canvas
import android.os.Build
import android.os.Vibrator
import android.util.Log
import android.view.SurfaceHolder
import java.lang.Exception
import java.lang.IllegalArgumentException

class GameLoop(private var game: Game, private var surfaceHolder: SurfaceHolder, private val vibrator: Vibrator?,private var playerLives: Int, private var enemyObject: EnemyObject): Thread() {

    private var isRunning = false
    private lateinit var canvas: Canvas
    private var averageUPS = 0.0
    private var averageFPS = 0.0

    private val UPS_PERIOD = 1E+3/MAX_UPS

    companion object{
        const val MAX_UPS = 60.0
    }

    fun getAverageUPS(): Double{
        return averageUPS
    }

    fun getAverageFPS(): Double{
        return averageFPS
    }

    fun startLoop(){
        //TODO Check playerLives for stopping and restarting the game
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
        var runningTime: Long = 0

        // Game Loop
        startTime = System.currentTimeMillis()
        while (isRunning){
            var startLoop = System.currentTimeMillis()

            checkRunningTime(runningTime)

            // update and render game
        try {
            canvas = surfaceHolder.lockCanvas()
            synchronized(surfaceHolder){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    game.update(vibrator)
                }
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    game.update(vibrator)
                }
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount*UPS_PERIOD - elapsedTime).toLong()
            }
            // Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000){
                averageUPS = (updateCount / (elapsedTime/1000)).toDouble()
                //Log.d("averageUPS", averageUPS.toString())
                averageFPS = frameCount / (elapsedTime/10).toDouble()
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
            runningTime = runningTime + (System.currentTimeMillis() - startLoop)

//            Log.d("Spawns per Minute", enemyObject.spawnPerMinute.toString())
//            Log.d("Spawns per Second", enemyObject.spawnPerSeconds.toString())
//            Log.d("Updates per spawn", enemyObject.updatesPerSpawn.toString())
//            Log.d("Updates until next Spawn", enemyObject.updatesUntilNextSpawn.toString())
        }
    }

    private fun checkRunningTime(runningTime: Long){
        if (runningTime in 5001..9999){
            game.enemyObjectVelocity = 30
            enemyObject.updatesPerSpawn = 30.0
        }
        if (runningTime in 10000..19999){
            game.enemyObjectVelocity = 40
            enemyObject.updatesPerSpawn = 25.0
        }
        if (runningTime in 20000..29999){
            game.enemyObjectVelocity = 45
            enemyObject.updatesPerSpawn = 20.0
        }
        if(runningTime > 30000){
            game.enemyObjectVelocity = 52
            enemyObject.updatesPerSpawn = 15.0
        }
    }
}