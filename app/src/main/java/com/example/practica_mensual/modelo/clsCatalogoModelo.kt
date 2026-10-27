package com.example.practica_mensual.modelo

class clsCatalogoModelo {
    suspend fun obtenerCatalogos(): List<dataCatalogo>{
        val dato = RetroFitAlumno.apiService.obtenerCatalogo()

        val listaFinal = mutableListOf(dataCatalogo(0, "Todas las carreras"))
        listaFinal.addAll(dato)
        return listaFinal
    }
}