package com.marino.receitas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marino.receitas.RecetaRepository
import com.marino.receitas.RecetaViewModel
import com.marino.receitas.adapter.RecetaAdapter
import com.marino.receitas.data.RecetaDatabase
import com.marino.receitas.databinding.FragmentListaRecetasBinding
import com.marino.receitas.model.Categoria
import com.marino.receitas.model.Dificultad
import com.marino.receitas.model.Receta
import com.marino.receitas.viewmodel.RecetaViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Fragmento que muestra la lista de recetas.
 * Requiere:
 * - Layout `fragment_lista_recetas.xml` con un RecyclerView de id `recyclerRecetas`.
 * - ViewBinding habilitado en build.gradle (`viewBinding = true`).
 */
class ListaRecetasFragment : Fragment() {

    // ViewBinding
    private var _binding: FragmentListaRecetasBinding? = null
    private val binding get() = _binding!!

    // ViewModel usando repository y factory
    private val viewModel: RecetaViewModel by viewModels {
        RecetaViewModelFactory(
            RecetaRepository(
                RecetaDatabase.getDatabase(requireContext()).recetaDao()
            )
        )
    }

    // Adapter para mostrar las recetas
    private lateinit var recetaAdapter: RecetaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaRecetasBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa el adapter con lista vacía
        recetaAdapter = RecetaAdapter(emptyList())

        // Configura el RecyclerView
        binding.recyclerRecetas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recetaAdapter
        }

        // Observa el flow de recetas y actualiza el adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todasLasRecetas.collectLatest { lista ->
                recetaAdapter.actualizarLista(lista)
            }
        }
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) Inicializa el adapter con lista vacía
        recetaAdapter = RecetaAdapter(emptyList())
        binding.recyclerRecetas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recetaAdapter
        }

        // 2) Inserta una receta de prueba si la BD está vacía
        viewLifecycleOwner.lifecycleScope.launch {
            // first() obtiene la lista actual sin hacer collect continuo
            if (viewModel.todasLasRecetas.first().isEmpty()) {
                viewModel.insertar(
                    Receta(
                        titulo      = "Tortilla de patatas",
                        categoria   = Categoria.ENTRANTES,
                        dificultad  = Dificultad.MEDIA,
                        coste       = 4.50,
                        ingredientes= "Patatas, huevos, cebolla, aceite, sal",
                        descripcion = "Cortar, freír y mezclar.",
                        tiempo      = 30
                    )
                )
            }
        }

        // 3) Ahora observa el flow y actualiza el adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todasLasRecetas.collectLatest { lista ->
                recetaAdapter.actualizarLista(lista)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


