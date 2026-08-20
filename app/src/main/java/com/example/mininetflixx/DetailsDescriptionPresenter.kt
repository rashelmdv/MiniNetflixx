package com.example.mininetflixx

import android.os.Bundle
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.example.mininetflixx.network.Video

class DetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(viewHolder: ViewHolder, item: Any) {
        val video = item as Video
        viewHolder.title.text = video.title
        viewHolder.subtitle.text = "Catálogo App"
        viewHolder.body.text = video.description
    }
}