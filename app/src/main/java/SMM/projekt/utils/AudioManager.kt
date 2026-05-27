package SMM.projekt.utils

import android.content.Context
import android.media.MediaPlayer
import SMM.projekt.R

object AudioManager {

    private var mediaPlayer: MediaPlayer? = null
    private var currentVolume: Float = 0.3f
    private var muted: Boolean = false

    fun setMuted(value: Boolean) {
        muted = value

        if (muted) {
            mediaPlayer?.pause()
        } else {
            mediaPlayer?.start()
        }

        applyVolume()
    }

    fun setVolume(value: Float) {
        currentVolume = value
        applyVolume()
    }

    private fun applyVolume() {
        val finalVolume = if (muted) 0f else currentVolume
        mediaPlayer?.setVolume(finalVolume, finalVolume)
    }
    fun start(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(
                context.applicationContext,
                R.raw.muzyka_tlo
            ).apply {
                isLooping = true
                start()
            }
            applyVolume()
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun resume() {
        if (!muted) {
            mediaPlayer?.start()
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}