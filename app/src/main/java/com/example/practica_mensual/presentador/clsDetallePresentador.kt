package com.example.practica_mensual.presentador
import com.example.practica_mensual.modelo.clsCursoModelo
import com.example.practica_mensual.modelo.dataImagenesCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class clsDetallePresentador(private val vista: VistaDetalle) {
    private val modelo = clsCursoModelo()

    interface VistaDetalle {
        fun mostrarGaleria(imagenes: List<dataImagenesCurso>)
        fun mostrarError(mensaje: String)
    }

    fun obtenerGaleriaCurso(cursoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val listaImagenes = modelo.obtenerDetalleCurso(cursoId)

                withContext(Dispatchers.Main) {
                    if (listaImagenes.isNotEmpty()) {
                        vista.mostrarGaleria(listaImagenes)
                    } else {
                        vista.mostrarError("No hay imágenes en la galería para este curso.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    vista.mostrarError("Error al cargar la galería: ${e.message}")
                }
            }
        }
    }


}