package com.example.mininetflixx

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy // <-- AGREGADO PARA EL CACHÉ
import com.example.mininetflixx.network.Video

class CardPresenter : Presenter() {

    private val cardWidth = 300
    private val cardHeight = 450

    // Nuestro propio ViewHolder manual, sin ImageCardView
    class ViewHolder(view: View) : Presenter.ViewHolder(view) {
        val imageView: ImageView = view as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        // Creamos un ImageView manualmente, sin usar ImageCardView
        val imageView = ImageView(parent.context)

        // --- AGREGADO: Margen entre imágenes (separación) ---
        val margin = 50
        val params = ViewGroup.MarginLayoutParams(cardWidth, cardHeight)
        params.setMargins(0, 0, margin, margin) // Margen a la derecha y abajo
        imageView.layoutParams = params
        // ---------------------------------------------------

        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val video = item as Video
        val myHolder = viewHolder as ViewHolder
        val imageView = myHolder.imageView

        // Cargamos la imagen con Glide
        Glide.with(imageView.context)
            .load(video.imageUrl)
            .override(cardWidth, cardHeight)
            .centerCrop()
            // --- AGREGADO: Limpiar caché para que cargue las imágenes nuevas ---
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            // ----------------------------------------------------------------
            .into(imageView)

        // Listener para abrir detalles
        imageView.setOnClickListener {
            if (imageView.context is MainActivity) {
                // --- AGREGADO: Antes de abrir la Activity, mostramos un Toast con el nombre y descripción ---
                android.widget.Toast.makeText(
                    imageView.context,
                    "${video.title}\n${video.description}",
                    android.widget.Toast.LENGTH_LONG
                ).show()
                // -----------------------------------------------------------------------------------------

                (imageView.context as MainActivity).openDetails(video)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        val myHolder = viewHolder as ViewHolder
        Glide.with(myHolder.imageView.context).clear(myHolder.imageView)
    }
}