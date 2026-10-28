package com.example.practica_mensual.modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import retrofit2.http.GET

@Serializable
data class dataImagenesCurso(
    @SerialName("id_imagen") val id_imagen: Int = 0,
    @SerialName("id_curso") val id_curso: Int = 0,
    @SerialName("url_imagen") val url_imagen: String = ""
)