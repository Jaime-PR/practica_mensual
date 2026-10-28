package com.example.practica_mensual.vistaa

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_mensual.R
import com.example.practica_mensual.adaptador.CursoAdapter
import com.example.practica_mensual.modelo.dataCategorias
import com.example.practica_mensual.modelo.dataCursosRegistros
import com.example.practica_mensual.presentador.clsCarreraPresentador

class VerRegistros : AppCompatActivity() {
    private lateinit var presenter: clsCarreraPresentador
    private lateinit var spinnerCarreras: Spinner
    private lateinit var recyclerViewCursos: RecyclerView
    private lateinit var cursoAdapter: CursoAdapter
    private lateinit var txtTotalGeneral: TextView
    private lateinit var btnVolverCatalogo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_registros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerCarreras = findViewById(R.id.spinnerCarreras)
        recyclerViewCursos = findViewById(R.id.recyclerCursos)
        txtTotalGeneral = findViewById(R.id.txtTotal)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)

        recyclerViewCursos.layoutManager = LinearLayoutManager(this)
        cursoAdapter = CursoAdapter(emptyList())
        recyclerViewCursos.adapter = cursoAdapter

        presenter = clsCarreraPresentador(this)

        configurarListeners()

        presenter.iniciar()
        btnVolverCatalogo.setOnClickListener(this:: volverCatalogo)
    }

    fun volverCatalogo(v: View){
        finish()
    }

    private fun configurarListeners(){
        spinnerCarreras.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val carreraSeleccionada = parent?.getItemAtPosition(position) as dataCategorias

                presenter.seleccionarCarrera(carreraSeleccionada.id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun mostrarCarreras(carrera: List<dataCategorias>){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carrera)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarreras.adapter = adapter
    }

    fun mostrarMensajeExito(id: Int, nombre: String){
        Toast.makeText(this, "ID: " + id.toString() + "-Seleccionaste: " + nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarEstadoInscripciones(totalGeneral: Int, listaCursos: List<dataCursosRegistros>) {
        txtTotalGeneral.text = "Total General de Inscritos: $totalGeneral"

        cursoAdapter.actualizarLista(listaCursos)
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}