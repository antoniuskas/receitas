package com.marino.receitas

import com.marino.receitas.model.Receta
import com.marino.receitas.model.RecetaDao
import kotlinx.coroutines.flow.Flow

class RecetaRepository(private val recetaDao: RecetaDao) {

    val todasLasRecetas: Flow<List<Receta>> = recetaDao.obterTodas()

    suspend fun obtenerPorId(id: Int): Receta? {
        return recetaDao.getById(id)
    }

    suspend fun insertar(receta: Receta) {
        recetaDao.insert(receta)
    }

    suspend fun borrar(receta: Receta) {
        recetaDao.delete(receta)
    }

    suspend fun actualizar(receta: Receta) {
        recetaDao.update(receta)
    }

    suspend fun borrarTodas() {
        recetaDao.deleteAll()
    }

}