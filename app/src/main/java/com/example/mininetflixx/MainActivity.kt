package com.example.mininetflixx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mininetflixx.network.Video

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Aquí está la magia: en lugar de 'commit', usamos 'commitNow' o 'commit' nativo
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commit()
        }
    }

    fun openDetails(video: Video) {
        val intent = Intent(this, DetailsActivity::class.java)
        // Corregimos el error de "Cast will always fail" usando un String para la key
        intent.putExtra("video", video as java.io.Serializable)
        startActivity(intent)
    }
}