package com.bornidea.basededatossqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class NuevoAlumno : AppCompatActivity() {

    var crud: AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        val id = findViewById<EditText>(R.id.edtId)
        val nombre = findViewById<EditText>(R.id.edtNombre)
        val bAdd = findViewById<Button>(R.id.btAdd)

        crud = AlumnoCRUD(this)

        bAdd.setOnClickListener {
            crud?.newAlumno(Alumno(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}