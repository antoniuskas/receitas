package com.marino.receitas.model
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
// marcamos la interfaz como un Data Access Object en Room
// vamos a definir los métodos que nos permiten trabajar con la BD
// consultas, insertar actualizar y borrar
// aunque no las utilicesmos ahora
@Dao
/*Room genera automáticamente el código SQL necesario a partir de las anotaciones (@Insert, @Query, etc.).
Te permite trabajar con objetos Receta sin escribir manualmente SQL.
Se usa desde el Repository y luego desde tu ViewModel.*/
interface RecetaDao {
    //****************CONSULTAR
    @Query("SELECT * FROM recetas")
    // con flow si se insertan actualizan o eliminan se actualiza la lista automaticamente
    // como trabaja en segundo plano no necesita suspend
    fun obterTodas(): Flow<List<Receta>> // cambios en tiempo real
    //*****************CONSULTA POR ID

    @Query("SELECT * FROM recetas WHERE id IN (:idReceta)")
    suspend fun getById(idReceta: Int): Receta?
    //*****************INSERTAR ********
    // para insertar en la base de datso
    // si existe una receta con el mismo id la reemplaza
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // para que se ejecute en una coroutina que es como un hilo
    // pero que no bloquea la app con suspend le decimos que son tareas
    // que se ejecutan en segundo plano, son más ligeras que los hilos
    suspend fun insert(receta: Receta)

    //********************BORRADO
    @Delete
    suspend fun delete(receta: Receta)
    // ***********ACTUALIZAR
    @Update
    suspend fun update(receta: Receta)
    //********************BORRAR TODAS
    @Query("DELETE FROM recetas")
    suspend fun deleteAll()

}