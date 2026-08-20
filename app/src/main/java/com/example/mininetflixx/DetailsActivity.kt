package com.example.mininetflixx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mininetflixx.network.Video

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details) // Asegúrate de tener este layout creado

        val video = intent.getSerializableExtra("video") as? Video ?: return
        setupDetails(video)
    }

    private fun setupDetails(video: Video) {
        // Aquí deberías cargar tu fragmento o llenar las vistas manualmente si no usas Leanback
        // Ejemplo básico para cargar imagen:
        // Glide.with(this).load(video.imageUrl).into(findViewById(R.id.imageView))
    }
}