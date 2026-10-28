package com.example.practica_mensual.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practica_mensual.R
import com.example.practica_mensual.modelo.dataCursos
import com.example.practica_mensual.vistaa.MainActivity
import com.example.practica_mensual.vistaa.VistaDetalleActivity
import com.example.practica_mensual.vistaa.RegistroCursoActivity


class CursosCatAdapter(
    private val activity: MainActivity,
    private var listaCursos: List<dataCursos>
) : RecyclerView.Adapter<CursosCatAdapter.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.txtMostrarNombreCurso)
        val tvSalon: TextView = itemView.findViewById(R.id.txtSalon)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)


        val btnDetalle: Button = itemView.findViewById(R.id.btnDetalle)
        val btnRegistro: Button =itemView.findViewById(R.id.btnRegistro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = listaCursos[position]
        holder.tvNombre.text = curso.nombre
        holder.tvSalon.text = "Salon: " + curso.espacio.toString()

        val baseURL = "http://192.168.0.132/cursos_api/"
        val urlImagenCompleta = baseURL + curso.urlImgPrincipal
        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)

        holder.btnDetalle.setOnClickListener {

            val intent = Intent(activity, VistaDetalleActivity::class.java).apply {
                putExtra("CURSO_ID", curso.id)
                putExtra("NOMBRE_CURSO", curso.nombre)
                putExtra("CODIGO_CURSO", curso.codigo)
                putExtra("DESCRIPCION", curso.descripcion)
                putExtra("ESPACIO", curso.espacio)
                putExtra("TOTAL_HORAS", curso.total_horas)
                putExtra("DIAS_ATENCION", curso.dias)
                putExtra("HORARIO", curso.horario)
                putExtra("CUPO", curso.cupo)
            }
            activity.startActivity(intent)
        }
        holder.btnRegistro.setOnClickListener {
            val intent = Intent(activity, RegistroCursoActivity::class.java).apply {
                putExtra("CURSO_ID", curso.id)
                putExtra("NOMBRE_CURSO", curso.nombre)
            }
            activity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}