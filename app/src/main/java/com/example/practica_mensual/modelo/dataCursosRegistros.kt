package com.example.practica_mensual.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursosRegistros(
    @SerialName("id_curso") val idCurso: Int = 0,
    @SerialName("nombre_curso") val nombreCurso: String = "",
    @SerialName("url_imagen_principal") val urlImagenPrincipal: String? = null,
    @SerialName("cupo") val cupo: Int = 0,
    @SerialName("total_inscritos") val totalInscritos: Int = 0
)
