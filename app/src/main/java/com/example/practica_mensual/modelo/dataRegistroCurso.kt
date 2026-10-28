package com.example.practica_mensual.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroCurso(
    @SerialName("curso_id")
    val cursoId: Int,
    val matricula: String,
    @SerialName("fecha_registro")
    val fechaRegistro: String
)