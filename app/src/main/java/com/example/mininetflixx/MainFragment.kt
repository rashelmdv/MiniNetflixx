package com.example.mininetflixx

import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.Intent
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.example.mininetflixx.network.RetrofitClient
import com.example.mininetflixx.network.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : BrowseSupportFragment() {

    private lateinit var mRowsAdapter: ArrayObjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el adaptador principal
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // --- LISTENER DE SELECCIÓN (Estilo Netflix, sin Toast) ---
        onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is Video) {
                // Abrimos directamente la pantalla de detalles (estilo Netflix)
                val intent = Intent(requireContext(), DetailsActivity::class.java)
                intent.putExtra("video", item)
                startActivity(intent)
            }
        }
        // ---------------------------------------------------------

        // Cargar las películas
        fetchMovies()
    }

    private fun fetchMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movies = withContext(Dispatchers.IO) {
                    RetrofitClient.api.getMovies()
                }

                if (movies.isNotEmpty()) {
                    setupMovieRows(movies)
                    Log.d("MainFragment", "Películas cargadas: ${movies.size}")
                }
            } catch (e: Exception) {
                Log.e("MainFragment", "Error al cargar: ${e.message}")
            }
        }
    }

    private fun setupMovieRows(movies: List<Video>) {
        val cardAdapter = createCardAdapter(movies)

        val header = HeaderItem(0, "Películas Recomendadas")
        val listRow = ListRow(header, cardAdapter)
        mRowsAdapter.add(listRow)

        adapter = mRowsAdapter
    }

    private fun createCardAdapter(movies: List<Video>): ArrayObjectAdapter {
        val adapter = ArrayObjectAdapter(CardPresenter())
        for (movie in movies) {
            adapter.add(movie)
        }
        return adapter
    }
}