package com.example.practica_mensual.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroCurso(
    @SerialName("id_curso") val cursoId: Int,
    @SerialName("matricula") val matricula: String,
    @SerialName("fecha_registro") val fechaRegistro: String
)