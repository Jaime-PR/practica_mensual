package com.example.practica_mensual.presentador

import com.example.practica_mensual.modelo.clsCarreraModelo
import com.example.practica_mensual.modelo.clsCursoModelo
import com.example.practica_mensual.modelo.dataCategorias
import com.example.practica_mensual.vistaa.VerRegistros
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCarreraPresentador(private val vistaa: VerRegistros) {
    private val modeloCarrera = clsCarreraModelo()
    private val modeloCurso = clsCursoModelo()
    private var listaCarreras: List<dataCategorias> = emptyList()

    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modeloCarrera.obtenerCarreras()
                vistaa.mostrarCarreras(listaCarreras)
            }catch (e: Exception){
                vistaa.mostrarError("Error:" + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(idCarrera: Int){
        val carrera = listaCarreras.find { it.id == idCarrera }

        if (carrera != null) {
            vistaa.mostrarMensajeExito(carrera.id, carrera.carrera)
        } else {
            vistaa.mostrarMensajeExito(idCarrera, "Todas las carreras")
        }
        obtenerEstadoInscripciones(idCarrera)
    }

    fun obtenerEstadoInscripciones(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modeloCurso.obtenerEstadoInscripciones(carreraId)

                vistaa.mostrarEstadoInscripciones(respuesta.totalGeneralInscritos, respuesta.cursos)

            } catch (e: Exception) {
                vistaa.mostrarError("Error al cargar inscripciones: " + e.localizedMessage)
            }
        }
    }
}