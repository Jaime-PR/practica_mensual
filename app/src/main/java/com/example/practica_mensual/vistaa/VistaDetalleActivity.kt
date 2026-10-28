package com.example.practica_mensual.vistaa

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.practica_mensual.R
import com.example.practica_mensual.modelo.dataImagenesCurso
import com.example.practica_mensual.presentador.clsDetallePresentador

class VistaDetalleActivity : AppCompatActivity(), clsDetallePresentador.VistaDetalle {

    private lateinit var presentador: clsDetallePresentador
    private lateinit var imgDetalleCurso: ImageView
    private lateinit var btnVolver: Button
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtDetalleCodigoCurso: TextView
    private lateinit var txtDetalleDescripcionCurso: TextView
    private lateinit var txtDetalleEspacioCurso: TextView
    private lateinit var txtDetalleTotalHorasCurso: TextView
    private lateinit var txtDetalleDiasAtencionCurso: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgDetalleCurso = findViewById(R.id.imgDetalleCurso)
        btnVolver = findViewById(R.id.btnVolver)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtDetalleCodigoCurso = findViewById(R.id.txtCodigoCurso)
        txtDetalleDescripcionCurso = findViewById(R.id.txtDescripcionCurso)
        txtDetalleEspacioCurso = findViewById(R.id.txtEspacioCurso)
        txtDetalleTotalHorasCurso = findViewById(R.id.txtTotalHorasCurso)
        txtDetalleDiasAtencionCurso = findViewById(R.id.txtDiasAtencionCurso)
        configurarListeners()

        txtNombreCurso.text = intent.getStringExtra("NOMBRE_CURSO") ?: ""
        txtDetalleCodigoCurso.text = intent.getStringExtra("CODIGO_CURSO") ?: ""
        txtDetalleDescripcionCurso.text = intent.getStringExtra("DESCRIPCION") ?: ""
        txtDetalleEspacioCurso.text = intent.getStringExtra("ESPACIO") ?: ""

        val totalHoras = intent.getStringExtra("TOTAL_HORAS") ?: "0"
        txtDetalleTotalHorasCurso.text = "$totalHoras Horas"

        txtDetalleDiasAtencionCurso.text = intent.getStringExtra("DIAS_ATENCION") ?: ""

        presentador = clsDetallePresentador(this)
        val cursoId = intent.getIntExtra("CURSO_ID", 0)

        if (cursoId > 0) {
            presentador.obtenerGaleriaCurso(cursoId)
        } else {
            Toast.makeText(this, "Error: No se recibió el ID del curso", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configurarListeners() {
        btnVolver.setOnClickListener(this::cerrarVista)
    }

    private fun cerrarVista(v: View) {
        finish()
    }

    override fun mostrarGaleria(imagenes: List<dataImagenesCurso>) {
        if (imagenes.isNotEmpty()) {
            val urlCompleta = "http://192.168.1.11/cursos_api/" + imagenes[0].url_imagen

            Glide.with(this)
                .load(urlCompleta)
                .into(imgDetalleCurso)
        }
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}