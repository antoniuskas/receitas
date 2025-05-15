package com.marino.receitas.model

import com.marino.receitas.R

enum class Categoria(val resourceId: Int) {
    // devolvemos una cadena por cada  valor de la enun
    ENTRANTES(R.string.cat_entrantes),
    PESCADOS(R.string.cat_pescados),
    CARNES(R.string.cat_carnes),
    PASTAS_Y_ARROCES(R.string.cat_pastyarro),
    POSTRES(R.string.cat_postres);

    companion object {
        fun of(nome: String?): Categoria {
            // si es nulo devuelve ENTRANTES
            return entries.firstOrNull { it.name.equals(nome, true) } ?: ENTRANTES
        }
    }
    /* para utilizarlo utilizaríamos:
    val categoria = Categoria.of("CARNES")
    val texto = context.getString(categoria.resourceId)
     */
}