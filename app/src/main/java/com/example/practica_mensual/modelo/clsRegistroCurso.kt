package com.example.practica_mensual.modelo

import retrofit2.Response

// Clase Modelo MVP (debe ir primero para que Android Studio la reconozca como principal)
class clsRegistroCurso {

    suspend fun registrarAlumnoCurso(
        cursoId: Int,
        matricula: String,
        fechaRegistro: String
    ): Response<dataRespuestaInscripciones> {
        val request = RegistroRequest(
            cursoId = cursoId,
            matricula = matricula,
            fechaRegistro = fechaRegistro
        )
        return RetroFitAlumno.apiService.registrarCurso(request)
    }

    suspend fun obtenerAlumno(matricula: String): Response<AlumnoResponse> {
        return RetroFitAlumno.apiService.obtenerAlumnoPorMatricula(matricula)
    }
}

// Data classes
data class RegistroRequest(
    val cursoId: Int,
    val matricula: String,
    val fechaRegistro: String
)

data class AlumnoResponse(
    val exito: Boolean = false,
    val correo: String? = null
)