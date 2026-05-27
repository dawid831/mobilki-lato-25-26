package SMM.projekt.utils

import android.content.Context
import android.media.MediaPlayer
import SMM.projekt.R

object AudioManager {

    private var mediaPlayer: MediaPlayer? = null

    fun start(context: Context) {

        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer.create(
                context.applicationContext,
                R.raw.muzyka_tlo
            ).apply {
                isLooping = true
                setVolume(0.3f, 0.3f)
                start()
            }
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun resume() {
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}