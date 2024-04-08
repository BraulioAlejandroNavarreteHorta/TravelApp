package com.example.tdmpa_2p_ex_74570

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnAtras = findViewById<Button>(R.id.btnAtras)

        val nombre = intent?.getStringExtra("nombre")
        val destino = intent?.getStringExtra("destino")
        val fecha = intent?.getStringExtra("fecha")
        val duracion = intent?.getStringExtra("duracion")
        val categoria = intent?.getStringExtra("categoria")

        //intento.putExtra("categoria", chipSeleccionado())

        val nombreTextView = findViewById<TextView>(R.id.txtNombreViajeDetail)
        val destinoTextView = findViewById<TextView>(R.id.txtDestinoDetail)
        val fechaTextView = findViewById<TextView>(R.id.txtFechDetail)
        val duracionTextView = findViewById<TextView>(R.id.txtDuracionDetail)
        val txtCategoriaDetail = findViewById<TextView>(R.id.txtCategoriaDetail)

        nombreTextView.text = nombre
        destinoTextView.text = destino
        fechaTextView.text = fecha.toString()
        duracionTextView.text = duracion.toString()
        txtCategoriaDetail.text = categoria

        btnAtras.setOnClickListener{
            finish()
        }
    }
}