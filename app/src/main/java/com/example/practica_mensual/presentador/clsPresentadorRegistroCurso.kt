package com.example.practica_mensual.presentador

import com.example.practica_mensual.modelo.clsRegistroCurso
import com.example.practica_mensual.vistaa.RegistroCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class clsPresentadorRegistroCurso(private val view: RegistroCursoActivity) {
    private val modelo = clsRegistroCurso()

    fun cargarDatosCurso(nombreCurso: String, urlImagen: String) {
        if (urlImagen.isNotEmpty()) {
            view.mostrarImagenCurso(urlImagen)
        }
    }

    fun realizarRegistro(cursoId: Int, matricula: String, fechaRegistro: String) {
        if (cursoId <= 0 || matricula.isEmpty() || fechaRegistro.isEmpty()) {
            view.mostrarError("Por favor, completa todos los campos solicitados")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.registrarAlumnoCurso(cursoId, matricula, fechaRegistro)
                }

                if (respuesta.isSuccessful) {
                    view.registroExitoso("Inscripción registrada correctamente")
                } else {
                    val errorBody = respuesta.errorBody()?.string()
                    val mensajeError = try {
                        JSONObject(errorBody ?: "").optString("error", "Error al procesar la solicitud")
                    } catch (_: Exception) {
                        "Error al procesar la solicitud"
                    }
                    view.mostrarError(mensajeError)
                }
            } catch (e: Exception) {
                view.mostrarError("Error de conexión: ${e.localizedMessage ?: "Error desconocido"}")
            }
        }
    }

    fun buscarAlumnoPorMatricula(matricula: String) {
        if (matricula.isEmpty()) {
            view.autocompletarCorreo("")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.obtenerAlumno(matricula)
                }

                if (respuesta.isSuccessful && respuesta.body() != null) {
                    val alumno = respuesta.body()
                    val correoObtenido = alumno?.correo ?: ""
                    view.autocompletarCorreo(correoObtenido)
                } else {
                    view.autocompletarCorreo("")
                }
            } catch (_: Exception) {
                view.autocompletarCorreo("")
            }
        }
    }
}