package com.example.practica_mensual.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("id_curso") val id: Int = 0,
    @SerialName("id_carrera") val id_carrea: Int = 0,
    @SerialName("codigo_curso") val codigo: String = "",
    @SerialName("nombre_curso") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("espacio") val espacio: String = "",
    @SerialName("total_horas") val total_horas: String = "",
    @SerialName("horario") val horario: String = "",
    @SerialName("cupo") val cupo: String = "",
    @SerialName("url_imagen_principal") val urlImgPrincipal: String = "",
)