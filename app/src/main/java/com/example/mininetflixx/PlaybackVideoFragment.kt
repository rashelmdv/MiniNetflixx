package com.example.mininetflixx

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import com.example.mininetflixx.network.Video

/** Handles video playback with media controls. */
class PlaybackVideoFragment : VideoSupportFragment() {

    // Declaramos el "pegamento" que conecta el reproductor con los controles de la TV
    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtenemos el video
        val video = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().intent.getSerializableExtra("video") as? Video
        } else {
            @Suppress("DEPRECATION")
            requireActivity().intent.getSerializableExtra("video") as? Video
        }

        // Si el video es nulo, no hacemos nada y salimos
        if (video == null) return

        // Configuramos el sistema de reproducción (Glue)
        val glueHost = VideoSupportFragmentGlueHost(this)

        // Creamos el adaptador del reproductor
        val playerAdapter = MediaPlayerAdapter(requireContext())
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        // Conectamos el adaptador a los controles
        mTransportControlGlue = PlaybackTransportControlGlue(requireActivity(), playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.title = video.title
        mTransportControlGlue.subtitle = video.description

        // Le decimos al reproductor que cargue el video y reproduzca cuando esté listo
        playerAdapter.setDataSource(Uri.parse(video.url))
        mTransportControlGlue.playWhenPrepared()
    }

    override fun onPause() {
        super.onPause()
        // Pausamos el video si la app se va a segundo plano
        mTransportControlGlue.pause()
    }
}