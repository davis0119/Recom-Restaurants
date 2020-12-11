package com.example.restaurants

import android.content.Context
import android.media.MediaPlayer

object Audio {
    private var mp: MediaPlayer? = null
    private var lastResource: Int? = null

    fun playAudio(context: Context, id: Int, isLooping: Boolean = true) {
        createMediaPlayer(context, id)
        mp?.let {
            it.isLooping = isLooping
            if (!it.isPlaying) {
                it.start()
            }
        }
    }

    private fun createMediaPlayer(context: Context, id: Int) {
        // in case it's already playing something
        mp?.stop()
        mp = MediaPlayer.create(context, id)
        lastResource = id
    }

    fun pauseAudio() {
        mp?.pause()
    }

}