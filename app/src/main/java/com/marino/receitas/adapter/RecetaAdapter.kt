package com.marino.receitas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marino.receitas.databinding.ItemRecetaBinding
import com.marino.receitas.model.Receta

/**
 * Adapter para mostrar una lista de recetas en un RecyclerView.
 * @param recetas Lista de recetas a mostrar.
 * @param onItemClick Lambda que se ejecuta al pulsar un ítem (Receta). Por defecto, no hace nada.
 */
class RecetaAdapter(
    private var recetas: List<Receta>,
    private val onItemClick: (Receta) -> Unit = {} // Lambda vacía por defecto
) : RecyclerView.Adapter<RecetaAdapter.ViewHolder>() {

    // ViewHolder que contiene el binding del layout item_receta.xml
    inner class ViewHolder(val binding: ItemRecetaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla el layout de cada ítem y lo envuelve en un ViewHolder
        val binding = ItemRecetaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receta = recetas[position]
        with(holder.binding) {
            // Asigna los valores de la receta a las vistas
            textTitulo.text = receta.titulo
            textCategoria.text = root.context.getString(receta.categoria.resourceId)
            textDificultad.text = root.context.getString(receta.dificultad.resourceId)

            // Ejecuta el lambda al hacer clic en el ítem
            root.setOnClickListener { onItemClick(receta) }
        }
    }

    override fun getItemCount(): Int = recetas.size

    /**
     * Actualiza la lista de recetas y notifica los cambios al RecyclerView.
     */
    fun actualizarLista(nuevaLista: List<Receta>) {
        recetas = nuevaLista
        notifyDataSetChanged()
    }
}
