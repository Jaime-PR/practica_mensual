package com.example.practica_mensual.modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
class dataCatalogo (
    @SerialName("id_carrera") val id: Int = 0,
    @SerialName("nombre_carrera") val carrera: String=""
)
{
    override fun toString(): String {
        return carrera
    }
}