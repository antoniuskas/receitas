package com.marino.receitas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marino.receitas.model.Receta
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecetaViewModel(private val repository: RecetaRepository) : ViewModel() {

    val todasLasRecetas: StateFlow<List<Receta>> =
        repository.todasLasRecetas
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertar(receta: Receta) = viewModelScope.launch {
        repository.insertar(receta)
    }

    fun borrar(receta: Receta) = viewModelScope.launch {
        repository.borrar(receta)
    }

    fun actualizar(receta: Receta) = viewModelScope.launch {
        repository.actualizar(receta)
    }

    fun borrarTodas() = viewModelScope.launch {
        repository.borrarTodas()
    }

    suspend fun obtenerPorId(id: Int): Receta? {
        return repository.obtenerPorId(id)
    }
}