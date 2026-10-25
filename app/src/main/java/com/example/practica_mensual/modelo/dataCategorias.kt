package com.example.practica_mensual.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCategorias(
    @SerialName("id_carrera") val id: Int=0,
    @SerialName("nombre_carrera") val carrera: String=""
)
{
    override fun toString(): String {
        return carrera
    }
}
