package com.example.practica_mensual.vistaa

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.practica_mensual.R
import com.example.practica_mensual.presentador.clsPresentadorRegistroCurso
import java.util.Calendar

class RegistroCursoActivity : AppCompatActivity() {
    private lateinit var presenter: clsPresentadorRegistroCurso

    private lateinit var btnBack: ImageView
    private var tvNombreCursoTitulo: TextView? = null
    private lateinit var imgCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtNombreAlumno: EditText
    private lateinit var txtMatricula: EditText
    private lateinit var txtFechaRegistro: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button

    private var cursoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_curso)

        val rootView = findViewById<View>(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack = findViewById(R.id.btnRegresar)
        tvNombreCursoTitulo = findViewById(R.id.tvNombreCursoTitulo)
        imgCurso = findViewById(R.id.imgCurso)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtNombreAlumno = findViewById(R.id.txtNombreAlumno)
        txtMatricula = findViewById(R.id.txtMatricula)
        txtFechaRegistro = findViewById(R.id.txtFechaRegistro)
        etCorreo = findViewById(R.id.etCorreo)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)

        presenter = clsPresentadorRegistroCurso(this)

        cursoId = intent.getIntExtra("CURSO_ID", 0)
        val nombreCurso = intent.getStringExtra("NOMBRE_CURSO") ?: "CURSO SELECCIONADO"
        val urlImagen = intent.getStringExtra("IMAGEN_CURSO") ?: ""

        txtNombreCurso.text = nombreCurso
        tvNombreCursoTitulo?.text = nombreCurso
        presenter.cargarDatosCurso(cursoId, nombreCurso, urlImagen)

        txtMatricula.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val matricula = s.toString().trim()
                if (matricula.length >= 8) {
                    presenter.buscarAlumnoPorMatricula(matricula)
                } else {
                    etCorreo.setText("")
                }
            }
        })

        btnConfirmar.setOnClickListener { ejecutarRegistroCurso() }
        btnCancelar.setOnClickListener { finish() }
        btnBack.setOnClickListener { finish() }
        txtFechaRegistro.setOnClickListener { mostrarDatePicker() }
    }

    private fun ejecutarRegistroCurso() {
        val matricula = txtMatricula.text.toString().trim()
        val fechaRegistro = txtFechaRegistro.text.toString().trim()

        presenter.realizarRegistro(cursoId, matricula, fechaRegistro)
    }

    fun registroExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    fun mostrarImagenCurso(urlImagen: String) {
        Glide.with(this)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgCurso)
    }

    private fun mostrarDatePicker() {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = android.app.DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val fechaSeleccionada = String.format(java.util.Locale.getDefault(),"%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
                txtFechaRegistro.setText(fechaSeleccionada)
            },
            anio,
            mes,
            dia
        )
        datePicker.show()
    }

    fun autocompletarCorreo(correo: String) {
        etCorreo.setText(correo)
    }
}