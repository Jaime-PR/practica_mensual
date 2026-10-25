package com.example.practica_mensual.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRespuestaInscripciones(
    @SerialName(value = "exito") val exito: Boolean = false,
    @SerialName(value = "total_general_inscritos") val totalGeneralInscritos: Int = 0,
    @SerialName(value = "cursos") val cursos: List<dataCursosRegistros> = emptyList()
)