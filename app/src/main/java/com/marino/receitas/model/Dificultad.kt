package com.marino.receitas.model

import com.marino.receitas.R

enum class Dificultad (val resourceId: Int){
    BAJA(R.string.dif_baja),
    MEDIA(R.string.dif_media),
    ALTA(R.string.dif_alta);

    companion object {
        fun of(nome: String?): Dificultad {
            return entries.firstOrNull { it.name.equals(nome, true) }?: BAJA
        }
    }
}