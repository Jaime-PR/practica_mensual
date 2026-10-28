package com.example.practica_mensual.interfaceApi

import com.example.practica_mensual.modelo.dataAlumnoResponse
import com.example.practica_mensual.modelo.dataRegistroCurso
import com.example.practica_mensual.modelo.dataCatalogo
import com.example.practica_mensual.modelo.dataRespuestaInscripciones
import com.example.practica_mensual.modelo.dataCategorias
import com.example.practica_mensual.modelo.dataCursos
import com.example.practica_mensual.modelo.dataImagenesCurso
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carrera.php")
    suspend fun obtenerCarrera(): List<dataCategorias>

    @GET("obtener_carrera.php")
    suspend fun obtenerCatalogo(): List<dataCatalogo>

    @GET("obtener_cursos.php")
    suspend fun obtenerCurso(@Query("id_carrera") cursoId: Int): List<dataCursos>

    @GET("obtener_estado_inscripciones.php")
    suspend fun obtenerEstadoInscripciones(@Query("carrera_id") carreraId: Int): dataRespuestaInscripciones

    @GET("detalle_cursos.php")
    suspend fun obtenerDetalleCurso(
        @Query("curso_id") cursoId: Int
    ): List<dataImagenesCurso>

    @POST("registar_inscripcion.php")
    suspend fun registrarCurso(
        @Body request: dataRegistroCurso
    ): Response<dataRespuestaInscripciones>

    @GET("obtener_alumno.php")
    suspend fun obtenerAlumnoPorMatricula(
        @Query("matricula") matricula: String
    ): Response<dataAlumnoResponse>
}