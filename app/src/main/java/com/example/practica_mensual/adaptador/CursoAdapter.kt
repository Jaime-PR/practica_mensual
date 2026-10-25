package com.example.practica_mensual.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_mensual.R
import com.example.practica_mensual.modelo.dataCursosRegistros

class CursoAdapter(private var listaCursos: List<dataCursosRegistros>) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombreCurso: TextView = itemView.findViewById(R.id.txtNombreCurso)
        val progressCurso: ProgressBar = itemView.findViewById(R.id.progressCurso)
        val txtInscritos: TextView = itemView.findViewById(R.id.txtInscritos)
        val txtTotalCurso: TextView = itemView.findViewById(R.id.txtTotalCurso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        // Estilo exacto de la presentación con parámetros nombrados
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_curso_registro,  parent, false)
        return CursoViewHolder(itemView = view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = listaCursos[position]

        holder.txtNombreCurso.text = curso.nombreCurso
        holder.progressCurso.max = curso.cupo
        holder.progressCurso.progress = curso.totalInscritos
        holder.txtInscritos.text = "${curso.totalInscritos} inscritos"
        holder.txtTotalCurso.text = "/ ${curso.cupo} total"

        // Nota: Si en tu item_curso_registro también muestras la imagen principal con Glide
        // tal como en la diapositiva, puedes agregarla aquí usando la URL de tu servidor.
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursosRegistros>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}