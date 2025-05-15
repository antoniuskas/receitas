package com.marino.receitas.util

import android.util.Base64
import androidx.room.TypeConverter
import com.marino.receitas.model.Categoria
import com.marino.receitas.model.Dificultad

class Converters {

    @TypeConverter
    fun fromCategoria(categoria: Categoria): String = categoria.name

    @TypeConverter
    fun toCategoria(nombre: String): Categoria = Categoria.of(nombre)

    @TypeConverter
    fun fromDificultad(dificultad: Dificultad): String = dificultad.name

    @TypeConverter
    fun toDificultad(nombre: String): Dificultad = Dificultad.of(nombre)

    @TypeConverter
    fun fromByteArray(array: ByteArray?): String? =
        array?.let { Base64.encodeToString(it, Base64.DEFAULT) }

    @TypeConverter
    fun toByteArray(encoded: String?): ByteArray? =
        encoded?.let { Base64.decode(it, Base64.DEFAULT) }
}