package com.example.practica_mensual.modelo

class clsCursoCatModelo {
    suspend fun obtenerCursosPorCategoria(categoriaid: Int): List<dataCursos>{
        return RetroFitAlumno.apiService.obtenerCurso(categoriaid)
    }
}