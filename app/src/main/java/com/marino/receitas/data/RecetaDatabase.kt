package com.marino.receitas.data

import androidx.room.*
import android.content.Context
import com.marino.receitas.model.Receta
import com.marino.receitas.model.RecetaDao
import com.marino.receitas.util.Converters

// le decimos a Room que entidad vamos a usar Receta y que version
@Database(entities = [Receta::class], version = 1)
// vamos a usar conversores para los tipos enum y byteArray
@TypeConverters(Converters::class)
// Para cada clase DAO que se asoció con la base de datos esta BD debe definirf
// un método abstracto que tgenga cero argumentos y muestre una instancia de la clse DAO

abstract class RecetaDatabase : RoomDatabase() {
    abstract fun recetaDao(): RecetaDao

    companion object {
        @Volatile private var INSTANCE: RecetaDatabase? = null

        fun getDatabase(context: Context): RecetaDatabase {
            return INSTANCE ?: synchronized(this) {
                // creamos una instancia de la base de datos
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecetaDatabase::class.java,
                    "recetas_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}