package com.bornidea.basededatossqlite

import android.provider.BaseColumns

class AlumnosContract {
    //Clase dentro de otra clase (companion object)
    companion object {
        val VERSION = 1

        class Entrada : BaseColumns {
            companion object {
                val NOMBRE_TABLA = "alumnos"
                val COLUMNA_ID = "id"
                val COLUMNA_NOMBRE = "nombre"
            }
        }
    }
}
