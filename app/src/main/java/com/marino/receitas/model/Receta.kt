package com.marino.receitas.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
// cada instancia de receta representa una fila en una tabla Receta en la base de datos
data class Receta(
    // le decimos que genere la clave primaria y como no puede cambiar
    // lo declaramos como val, lo genera automaticamente autoincremento.
    // va a ser un entero, Room lo va a generar, nunca le da 0.
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    //Utilizo var pues pueden cambiar los valores
    var idReceta: Int = 0,
    var titulo: String,
    var categoria: Categoria = Categoria.ENTRANTES,
    var dificultad: Dificultad = Dificultad.BAJA,
    var coste: Double,
    var ingredientes: String,
    var descripcion: String,
    var tiempo: Int,
    var photo: ByteArray?=null
)
