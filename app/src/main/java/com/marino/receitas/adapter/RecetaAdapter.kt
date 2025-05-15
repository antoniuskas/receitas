package com.marino.receitas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marino.receitas.databinding.ItemRecetaBinding
import com.marino.receitas.model.Receta
/* 3l proceso quedaría así
item_receta.xml --> (LayoutInflater)--> Vista real --> ViewHolder --> RecyclerView
 */
// recibe una lista de recetas y unña función que se ejecuta al pulsar sobre una de ellas
class RecetaAdapter( // hereda de RecyclerView
    // lista de recetas a mostrar
    private var recetas: List<Receta>,
    // esta función se ejecuta al hacer clic
    private val onItemClick: (Receta) -> Unit
) : RecyclerView.Adapter<RecetaAdapter.ViewHolder>() {
// clase interna que conteine un objeto ItemRecetaBinding (eso se creo por el layout item_receta_xml)
    // utiliza el método de convertir el nombrfe xml a PascalCase y añadir Binding
    inner class ViewHolder(val binding: ItemRecetaBinding) : RecyclerView.ViewHolder(binding.root)
    // este método infla el layout de cada recetea item_receta.xml
    // el recyclerView no crea todas las vista de una vez, sería muy lento si son muchos
    // solo crea infla unas pocas al principio
    // luego reutiliza las vista mientras vas haciendo scroll con el raton
    // llama a este método cuando se muestra por primera vez.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecetaBinding.inflate( // itemRecetaBinding convierte el xml en una vista
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        // devuelve una clase contenedora para la vista inflada
        // el ViewHolder guarda referencias a los elementos visuales de cada campo, textTitulo, textCategoria, etc.
        // eso para que no los haya que buscar con findViewById
        return ViewHolder(binding)
    }
    // obtenemos la receta correspondiente a la posicion actual del RecyclerView
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receta = recetas[position]

        with(holder.binding) {
            textTitulo.text = receta.titulo
            textCategoria.text = root.context.getString(receta.categoria.resourceId)
            textDificultad.text = root.context.getString(receta.dificultad.resourceId)

            root.setOnClickListener {
                onItemClick(receta)
            }
        }
    }

    override fun getItemCount(): Int = recetas.size
    // permitir actualizar la lista
    fun actualizarLista(nuevaLista: List<Receta>) {
        recetas = nuevaLista
        notifyDataSetChanged()
    }
}