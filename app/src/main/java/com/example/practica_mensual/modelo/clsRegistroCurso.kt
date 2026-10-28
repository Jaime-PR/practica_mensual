package com.example.practica_mensual.modelo

import retrofit2.Response

class clsRegistroCurso {

    suspend fun registrarAlumnoCurso(
        cursoId: Int,
        matricula: String,
        fechaRegistro: String
    ): Response<dataRespuestaInscripciones> {
        val request = dataRegistroCurso(
            cursoId = cursoId,
            matricula = matricula,
            fechaRegistro = fechaRegistro
        )
        return RetroFitAlumno.apiService.registrarCurso(request)
    }

    suspend fun obtenerAlumno(matricula: String): Response<dataAlumnoResponse> {
        return RetroFitAlumno.apiService.obtenerAlumnoPorMatricula(matricula)
    }
}