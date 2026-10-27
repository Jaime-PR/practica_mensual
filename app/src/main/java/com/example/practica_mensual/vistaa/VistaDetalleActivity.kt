package com.example.practica_mensual.vistaa
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practica_mensual.R
import com.example.practica_mensual.modelo.dataImagenesCurso
import com.example.practica_mensual.presentador.clsDetallePresentador

class VistaDetalleActivity : AppCompatActivity(), clsDetallePresentador.VistaDetalle {

    private lateinit var presentador: clsDetallePresentador
    private lateinit var imgDetalleCurso: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_detalle)

        imgDetalleCurso = findViewById(R.id.imgDetalleCurso)
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val txtNombreCurso = findViewById<TextView>(R.id.txtNombreCurso)
        val txtDetalleCodigoCurso = findViewById<TextView>(R.id.txtCodigoCurso)
        val txtDetalleDescripcionCurso = findViewById<TextView>(R.id.txtDescripcionCurso)
        val txtDetalleEspacioCurso = findViewById<TextView>(R.id.txtEspacioCurso)
        val txtDetalleTotalHorasCurso = findViewById<TextView>(R.id.txtTotalHorasCurso)
        val txtDetalleDiasAtencionCurso = findViewById<TextView>(R.id.txtDiasAtencionCurso)

        val cursoId = intent.getIntExtra("CURSO_ID", 0)

        txtNombreCurso.text = intent.getStringExtra("NOMBRE") ?: ""
        txtDetalleCodigoCurso.text = intent.getStringExtra("CODIGO") ?: ""
        txtDetalleDescripcionCurso.text = intent.getStringExtra("DESCRIPCION") ?: ""
        txtDetalleEspacioCurso.text = intent.getStringExtra("ESPACIO") ?: ""

        val totalHoras = intent.getIntExtra("TOTAL_HORAS", 0)
        txtDetalleTotalHorasCurso.text = "$totalHoras Horas"

        txtDetalleDiasAtencionCurso.text = intent.getStringExtra("DIAS_ATENCION") ?: ""

        btnVolver.setOnClickListener {
            finish()
        }

        presentador = clsDetallePresentador(this)
        if (cursoId > 0) {
            presentador.obtenerGaleriaCurso(cursoId)
        } else {
            Toast.makeText(this, "Error: No se recibió el ID del curso", Toast.LENGTH_SHORT).show()
        }
    }

    override fun mostrarGaleria(imagenes: List<dataImagenesCurso>) {
        if (imagenes.isNotEmpty()) {
            val urlCompleta = "http://10.0.2.2/cursos_api/" + imagenes[0].url_imagen

            Glide.with(this)
                .load(urlCompleta)
                .into(imgDetalleCurso)
        }
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}