package com.example.practica_mensual.vistaa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_mensual.R
import com.example.practica_mensual.adaptador.CursosCatAdapter
import com.example.practica_mensual.modelo.dataCatalogo
import com.example.practica_mensual.modelo.dataCategorias
import com.example.practica_mensual.modelo.dataCursos
import com.example.practica_mensual.presentador.clsCatalogoPresentador

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: clsCatalogoPresentador
    private lateinit var spinnerCatalogo: Spinner
    private lateinit var recyclerCursos : RecyclerView
    private lateinit var btnVerRegistros : Button
    private lateinit var adapaterCursos: CursosCatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinnerCatalogo = findViewById(R.id.spnCarrera)
        recyclerCursos = findViewById(R.id.rvCursos)
        btnVerRegistros = findViewById(R.id.btnVerRegistros)

        recyclerCursos.layoutManager = GridLayoutManager(this, 2)
        adapaterCursos = CursosCatAdapter(this, emptyList())
        recyclerCursos.adapter = adapaterCursos

        presenter = clsCatalogoPresentador(this)
        configurarListeners()
        presenter.iniciar()

        btnVerRegistros.setOnClickListener(this:: abrirVerRegistros)
    }

    fun abrirVerRegistros(v: View){
        val intent = Intent(this, VerRegistros::class.java)
        startActivity(intent)
    }

    fun configurarListeners(){
        spinnerCatalogo.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val carreraSeleccionada = parent?.getItemAtPosition(position) as dataCatalogo
                presenter.seleccionarCatalogo(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun mostraraCatalogo(catalogo: List<dataCatalogo>){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, catalogo)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCatalogo.adapter = adapter
    }

    fun mostrarCurso(cursos: List<dataCursos>){
        adapaterCursos.actualizarLista(cursos)
    }

    fun mostrarMensajeExito(id: Int, nombre: String){
        Toast.makeText(this, "ID: " + id.toString() + "-Seleccionaste: " + nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

}