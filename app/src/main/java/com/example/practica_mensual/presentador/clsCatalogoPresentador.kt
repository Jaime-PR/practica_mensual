package com.example.practica_mensual.presentador

import com.example.practica_mensual.modelo.clsCursoCatModelo
import com.example.practica_mensual.modelo.clsCatalogoModelo
import com.example.practica_mensual.modelo.dataCatalogo
import com.example.practica_mensual.modelo.dataCursos
import com.example.practica_mensual.vistaa.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCatalogoPresentador(private val vista: MainActivity) {
    private val modeloCatalogo = clsCatalogoModelo()
    private val modeloCursos = clsCursoCatModelo()
    private var listaCatalogo: List<dataCatalogo> = emptyList()


    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCatalogo = modeloCatalogo.obtenerCatalogos()
                vista.mostraraCatalogo(listaCatalogo)
            }catch (e: Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCatalogo(posicion: Int){
        if(posicion >= 0 && posicion < listaCatalogo.size){
            val catalogo = listaCatalogo[posicion]
            vista.mostrarMensajeExito(catalogo.id, catalogo.carrera)
            cargarCurso(catalogo.id)
        }
    }

    private fun cargarCurso(categoriaid: Int){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cursos = modeloCursos.obtenerCursosPorCategoria(categoriaid)
                vista.mostrarCurso(cursos)
            }catch (e: Exception){
                vista.mostrarError("Error al cargar los cursos: " + e.localizedMessage)
            }
        }
    }
}