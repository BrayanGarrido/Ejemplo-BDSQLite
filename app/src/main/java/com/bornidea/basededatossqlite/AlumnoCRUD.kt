package com.bornidea.basededatossqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class AlumnoCRUD(context: Context) {

    private var helper: DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newAlumno(item: Alumno) {
        //ABRIR LA BD EN MODO ESCRITURA
        val db = helper?.writableDatabase

        //MAPEO DE COLUMNAS CON VALORES A INSERTAR
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        //INSERTAR UNA NUEVA FILA EN LA TABLA
        db?.insert(AlumnosContract.Companion.Entrada.NOMBRE_TABLA, null, values)
        db?.close()
    }

    fun getAlumnos(): ArrayList<Alumno> {

        val items: ArrayList<Alumno> = ArrayList()

        //ABRIR DB EN MODO LECTURA
        val db = helper?.readableDatabase

        //ESPECIFICAR LAS COLUMNAS QUE QUIERO CONSULTAR
        val columnas = arrayOf(
            AlumnosContract.Companion.Entrada.COLUMNA_ID,
            AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        //CREAR UN CURSOR PARA RECORRER LA TABLA
        val c: Cursor = db!!.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null, //para que mande todos los datos
            null,
            null,
            null,
            null
        )

        //HACER EL RECORRIDO DEL CURSOR EN LA TABLA
        while (c.moveToNext()) {
            items.add(
                Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
                )
            )
        }
        c.close()
        db.close()

        return items
    }

    //Obtener elemento por ID
    fun getAlumno(id: String): Alumno {
        var item: Alumno? = null

        val db = helper?.readableDatabase

        val columnas = arrayOf(
            AlumnosContract.Companion.Entrada.COLUMNA_ID,
            AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        val c: Cursor = db!!.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " id = ?", //? se mapea con el id del array of de abajo
            arrayOf(id),
            null,
            null,
            null
        )

        while (c.moveToNext()) {
            item = Alumno(
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
            )
        }
        c.close()
        db.close()

        return item!!
    }

    fun updateAlumno(item: Alumno) {
        val db = helper?.writableDatabase

        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        db!!.update(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            values, " id = ?",
            arrayOf(item.id)
        )
        db.close()
    }

    fun deleteAlumno(item: Alumno) {
        val db = helper?.writableDatabase

        db!!.delete(AlumnosContract.Companion.Entrada.NOMBRE_TABLA, " id= ?", arrayOf(item.id))

        db.close()
    }
}