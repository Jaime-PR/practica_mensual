package com.example.practica_mensual.modelo

class clsCursoModelo {
    suspend fun obtenerEstadoInscripciones(carreraId: Int): dataRespuestaInscripciones {
        return RetroFitAlumno.apiService.obtenerEstadoInscripciones(carreraId)
    }
}