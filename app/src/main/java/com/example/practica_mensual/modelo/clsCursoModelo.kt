package com.example.practica_mensual.modelo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class clsCursoModelo {
    suspend fun obtenerEstadoInscripciones(carreraId: Int): dataRespuestaInscripciones {
        return RetroFitAlumno.apiService.obtenerEstadoInscripciones(carreraId)
    }

    suspend fun obtenerDetalleCurso(cursoId: Int): List<dataImagenesCurso> {
        return withContext(Dispatchers.IO) {

            val api = RetroFitAlumno.apiService
            api.obtenerDetalleCurso(cursoId)
        }
    }
}