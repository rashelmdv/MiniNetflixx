package com.example.mininetflixx

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class BrowseErrorActivity : AppCompatActivity() {

    private lateinit var mErrorFragment: ErrorSimpleFragment
    private lateinit var mSpinnerFragment: SpinnerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Usamos el layout genérico (asegúrate de tener activity_main.xml con ese ID o cambia el ID)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
        testError()
    }

    private fun testError() {
        // 1. Crear y agregar el fragmento de error
        mErrorFragment = ErrorSimpleFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_browse_fragment, mErrorFragment)
            .commit()

        // 2. Crear y agregar el fragmento de carga (Spinner)
        mSpinnerFragment = SpinnerFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_browse_fragment, mSpinnerFragment)
            .commit()

        // 3. Simular una carga y luego mostrar el error
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // Quitamos el spinner
            supportFragmentManager.beginTransaction()
                .remove(mSpinnerFragment)
                .commit()

            // El fragmento de error ahora se ve gracias al layout XML que creamos
        }, 3000L)
    }

    // Fragmento de error simple (sin usar la obsoleta ErrorFragment)
    class ErrorSimpleFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Cargamos el layout que creamos en el Paso 1
            return inflater.inflate(R.layout.fragment_error, container, false)
        }
    }

    class SpinnerFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val progressBar = ProgressBar(container?.context)
            if (container is FrameLayout) {
                val layoutParams =
                    FrameLayout.LayoutParams(100, 100, android.view.Gravity.CENTER)
                progressBar.layoutParams = layoutParams
            }
            return progressBar
        }
    }
}