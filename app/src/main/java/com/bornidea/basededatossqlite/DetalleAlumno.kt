package com.bornidea.basededatossqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class DetalleAlumno : AppCompatActivity() {

    var ID: String? = null
    var crud: AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_alumno)

        val id = findViewById<EditText>(R.id.edtId)
        val nombre = findViewById<EditText>(R.id.edtNombre)
        val bUpdate = findViewById<Button>(R.id.btUpdate)
        val bDelete = findViewById<Button>(R.id.btDelete)
        ID = intent.getStringExtra("ID")
        crud = AlumnoCRUD(this)

        //Obtener alumno de la base
        val alumno = crud?.getAlumno(ID!!)

        id.setText(alumno?.id)
        nombre.setText(alumno?.nombre)

        bUpdate.setOnClickListener {
            crud?.updateAlumno(Alumno(id.text.toString(),nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))

        }
        bDelete.setOnClickListener {
            crud?.deleteAlumno(Alumno(id.text.toString(),nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}