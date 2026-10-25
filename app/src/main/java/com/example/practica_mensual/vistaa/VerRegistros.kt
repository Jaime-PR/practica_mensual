package com.example.practica_mensual.vistaa

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_registros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerCarreras = findViewById(R.id.spinnerCarreras) // Ajusta el ID según tu XML
        recyclerViewCursos = findViewById(R.id.recyclerCursos)   // Ajusta el ID según tu XML
        txtTotalGeneral = findViewById(R.id.txtTotal)

        // Configurar RecyclerView
        recyclerViewCursos.layoutManager = LinearLayoutManager(this)
        cursoAdapter = CursoAdapter(emptyList())
        recyclerViewCursos.adapter = cursoAdapter

        // Inicializar Presentador (pasándole esta Activity como Vista)
        presenter = clsCarreraPresentador(this)

        configurarListeners()

        // Iniciar la carga de las categorías/carreras para el Spinner
        presenter.iniciar()
    }

    private fun configurarListeners(){
        spinnerCarreras.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Obtenemos el objeto completo de la posición seleccionada
                val carreraSeleccionada = parent?.getItemAtPosition(position) as dataCategorias

                // Le pasamos el ID real al presentador
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
        // Pintamos el número total de inscritos en la parte superior
        txtTotalGeneral.text = "Total General de Inscritos: $totalGeneral"

        // Actualizamos las tarjetas y barras de progreso del RecyclerView
        cursoAdapter.actualizarLista(listaCursos)
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}