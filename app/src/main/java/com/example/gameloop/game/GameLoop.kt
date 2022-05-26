package com.example.gameloop.game

import android.graphics.Canvas
import android.os.Build
import android.os.Vibrator
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
        var runningTime: Long = 0
    }

    fun getAverageUPS(): Double{
        return averageUPS
    }

    fun getAverageFPS(): Double{
        return averageFPS
    }

    fun startLoop(){
        isRunning = true
        Score.scoreCounter = 0.0
        runningTime = 0
        start()
    }

    fun resumeLoop(){
        isRunning = true
    }

    fun stopLoop(){
        isRunning = false
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

            var startLoop = System.currentTimeMillis()

            incrementEnemyVelocityWhileGameIsRunning(runningTime)

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
//            // Skip frames to keep up with the target UPS
//            while (sleepTime < 0 && updateCount < MAX_UPS -1){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    game.update(vibrator)
//                }
//                updateCount++
//                elapsedTime = System.currentTimeMillis() - startTime
//                sleepTime = (updateCount*UPS_PERIOD - elapsedTime).toLong()
//            }
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

            // wait 2 sec at the beginning
            if(runningTime <= 0){
                sleep(1000)
            }
            runningTime = runningTime + (System.currentTimeMillis() - startLoop)

//            Log.d("Spawns per Minute", enemyObject.spawnPerMinute.toString())
//            Log.d("Spawns per Second", enemyObject.spawnPerSeconds.toString())
//            Log.d("Updates per spawn", enemyObject.updatesPerSpawn.toString())
//            Log.d("Updates until next Spawn", enemyObject.updatesUntilNextSpawn.toString())
        }
    }

    /** Increments the level of difficulty while the game is running */
    private fun incrementEnemyVelocityWhileGameIsRunning(runningTime: Long){
        if (runningTime in 3000..5999){ // +1 CYC
            game.enemyObjectVelocity = 18
        }
        if (runningTime in 6000..9999){
            game.enemyObjectVelocity = 20
            enemyObject.updatesPerSpawn = 46.0
        }
        if (runningTime in 10000..15999){
            game.enemyObjectVelocity = 22
            enemyObject.updatesPerSpawn = 44.0
        }
        if(runningTime in 16000..21999){
            game.enemyObjectVelocity = 24
            enemyObject.updatesPerSpawn = 42.0
        }
        if(runningTime in 22000..29999){
            game.enemyObjectVelocity = 26
            enemyObject.updatesPerSpawn = 40.0
        }
        if(runningTime in 30000..33999){
            game.enemyObjectVelocity = 28
            enemyObject.updatesPerSpawn = 38.0
        }
        if(runningTime in 34000..37999){
            game.enemyObjectVelocity = 30
            enemyObject.updatesPerSpawn = 36.0
        }
        if(runningTime in 38000..41999){
            game.enemyObjectVelocity = 32
            enemyObject.updatesPerSpawn = 34.0
        }
        if(runningTime in 42000..45999){
            game.enemyObjectVelocity = 34
            enemyObject.updatesPerSpawn = 32.0
        }
        if(runningTime in 46000..59999){
            game.enemyObjectVelocity = 36
            enemyObject.updatesPerSpawn = 30.0
        }
        if(runningTime in 60000..69999){
            game.enemyObjectVelocity = 38
            enemyObject.updatesPerSpawn = 28.0
        }
        if(runningTime in 70000..79999){
            game.enemyObjectVelocity = 40
            enemyObject.updatesPerSpawn = 26.0
        }
    }
}