package com.example.practica_mensual.interfaceApi

import com.example.practica_mensual.modelo.AlumnoResponse
import com.example.practica_mensual.modelo.RegistroRequest
import com.example.practica_mensual.modelo.dataCategorias
import com.example.practica_mensual.modelo.dataCursosRegistros
import com.example.practica_mensual.modelo.dataRespuestaInscripciones
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    // Petición para obtener la lista de carreras
    @GET("obtener_carrera.php") // Reemplaza por la ruta de tu API PHP si difiere
    suspend fun obtenerCarrera(): List<dataCategorias>

    // Petición para obtener los cursos (usada en clsCursoModelo)
    @GET("obtener_curso.php") // Reemplaza por la ruta de tu API PHP si difiere
    suspend fun obtenerCurso(): List<dataCursosRegistros>

    // Peticiones para el registro de cursos
    @POST("registrar_curso.php")
    suspend fun registrarCurso(
        @Body request: RegistroRequest
    ): Response<dataRespuestaInscripciones>

    @GET("obtener_alumno.php")
    suspend fun obtenerAlumnoPorMatricula(
        @Query("matricula") matricula: String
    ): Response<AlumnoResponse>

    @GET("obtener_estado_inscripciones.php")
    suspend fun obtenerEstadoInscripciones(@Query("carrera_id") carreraId: Int): dataRespuestaInscripciones
}