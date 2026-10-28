package com.example.practica_mensual.modelo

import kotlinx.serialization.Serializable

@Serializable
data class dataAlumnoResponse(
    val id: Int? = null,
    val matricula: String? = null,
    val nombre: String? = null,
    val correo: String? = null
)