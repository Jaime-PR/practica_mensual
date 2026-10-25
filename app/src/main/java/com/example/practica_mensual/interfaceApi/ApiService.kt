package com.example.practica_mensual.interfaceApi

import com.example.practica_mensual.modelo.dataRespuestaInscripciones
import com.example.practica_mensual.modelo.dataCategorias
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carrera.php")
    suspend fun obtenerCarrera(): List<dataCategorias>

    @GET("obtener_estado_inscripciones.php")
    suspend fun obtenerEstadoInscripciones(@Query("carrera_id") carreraId: Int): dataRespuestaInscripciones
}