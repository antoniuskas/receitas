package com.marino.receitas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marino.receitas.ui.ListaRecetasFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Solo añadimos el fragment si no hemos restaurado uno previo
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ListaRecetasFragment(),
                    "ListaRecetas"  // tag opcional
                )
                .commit()
        }
    }
}