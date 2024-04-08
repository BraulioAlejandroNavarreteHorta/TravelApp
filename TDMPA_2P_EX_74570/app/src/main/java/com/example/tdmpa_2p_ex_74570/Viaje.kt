package com.example.tdmpa_2p_ex_74570

import java.util.Date

class Viaje(val nombre: String, val destino: String, val fecha: Date, val duracion: Int, val categoria:String) {
    init {
        println("Destino: $nombre")
        println("Destino: $destino")
        println("Fecha: $fecha")
        println("Duración: $duracion días")
        println("Categoria: $categoria ")

    }
}

