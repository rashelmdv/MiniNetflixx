package com.example.mininetflixx

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.leanback.app.PlaybackSupportFragment
import androidx.leanback.widget.PlaybackControlsRow
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.example.mininetflixx.network.Video
import androidx.leanback.widget.PlaybackControlsRow.PlayPauseAction

class PlaybackActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        val video = intent.getSerializableExtra("video") as? Video ?: return

        setupPlayer(video.url)

        // Encontramos el fragment creado en el XML
        val playbackFragment = supportFragmentManager.findFragmentById(R.id.playback_fragment) as? PlaybackSupportFragment

        playbackFragment?.let { fragment ->
            // Crear los controles
            val controlsRow = PlaybackControlsRow(video)


            // Botón de play/pausa
            val playPauseAction = PlaybackControlsRow.PlayPauseAction(this)

            // VINCULAR EL REPRODUCTOR AL FRAGMENT (Obligatorio)
        }
    }

    private fun setupPlayer(url: String) {
        val trackSelector = DefaultTrackSelector(this)
        player = ExoPlayer.Builder(this)
            .setTrackSelector(trackSelector)
            .build()

        val dataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(url)

        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.playWhenReady = true
    }

    override fun onDestroy() {
        player?.release()
        super.onDestroy()
    }
}