package com.example.practica_mensual.modelo

class clsCarreraModelo {
    suspend fun obtenerCarreras(): List<dataCategorias>{
        val datos = RetroFitAlumno.apiService.obtenerCarrera()

        val listaFinal = mutableListOf(dataCategorias(0, "Todas las carreras"))
        listaFinal.addAll(datos)
        return listaFinal
    }
}