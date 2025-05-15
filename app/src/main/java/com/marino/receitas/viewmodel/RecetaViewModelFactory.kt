package com.marino.receitas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marino.receitas.RecetaRepository
import com.marino.receitas.RecetaViewModel

//El ViewModelFactory es el encargado de crear  tu viewModel cuando necesita parámetros personalizados
// por defecto android crea ViewModel vacios
// como RecetaViewModel necesita un RecetaRepository, Android no lo crea solo.
class RecetaViewModelFactory(private val repository: RecetaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecetaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecetaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}