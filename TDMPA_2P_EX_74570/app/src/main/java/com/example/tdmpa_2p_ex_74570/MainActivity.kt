package com.example.tdmpa_2p_ex_74570

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtNombreViaje = findViewById<EditText>(R.id.txtNombreViaje)
        var txtDestino = findViewById<EditText>(R.id.txtDestino)
        var txtFecha = findViewById<EditText>(R.id.txtFecha)
        var txtDuracion = findViewById<EditText>(R.id.txtDuración)

        var btnAgregar = findViewById<Button>(R.id.btnAgregar)

        var spnFiltro = findViewById<Spinner>(R.id.spnFiltro)
        val categorias =resources.getStringArray(R.array.Filtro)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,categorias)
        spnFiltro.adapter=adapter

        spnFiltro.onItemSelectedListener=object :
            AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id:Long)
            {
                when {
                    (categorias[position].toString() == "Todos") -> {
                        val hoy = Date()
                        val chpGroup=findViewById<ChipGroup>(R.id.chpGroup)
                        chpGroup.removeAllViews()
                        for (viaje in viajeList) {

                            val chip= Chip(this@MainActivity)
                            chip.text= viaje.nombre
                            chip.isClickable=true
                            chip.isCheckable=false
                            chip.isCloseIconVisible = true

                            chip.setOnCloseIconClickListener {
                                chpGroup.removeView(chip)
                                viajeList.remove(viaje)}
                            chpGroup.addView(chip as View)

                            chip.setOnClickListener{
                                val intento = Intent(this@MainActivity, DetailActivity::class.java)
                                intento.putExtra("nombre", viaje.nombre)
                                intento.putExtra("destino", viaje.destino)
                                intento.putExtra("fecha", SimpleDateFormat("dd/MM/yyyy").format(viaje.fecha))
                                intento.putExtra("duracion", viaje.duracion.toString())
                                intento.putExtra("categoria", viaje.categoria)
                                startActivity(intento)
                            }

                        }
                    }

                    (categorias[position].toString() == "Próximos") -> {
                        val hoy = Date()
                        val chpGroup=findViewById<ChipGroup>(R.id.chpGroup)
                        chpGroup.removeAllViews()
                        for (viaje in viajeList) {
                            if (viaje.fecha.after(hoy)) {
                                val chip= Chip(this@MainActivity)
                                chip.text= viaje.nombre
                                chip.isClickable=true
                                chip.isCheckable=false
                                chip.isCloseIconVisible = true

                                chip.setOnCloseIconClickListener {
                                    chpGroup.removeView(chip)
                                    viajeList.remove(viaje)}
                                chpGroup.addView(chip as View)

                                chip.setOnClickListener{
                                    val intento = Intent(this@MainActivity, DetailActivity::class.java)
                                    intento.putExtra("nombre", viaje.nombre)
                                    intento.putExtra("destino", viaje.destino)
                                    intento.putExtra("fecha", SimpleDateFormat("dd/MM/yyyy").format(viaje.fecha))
                                    intento.putExtra("duracion", viaje.duracion.toString())
                                    intento.putExtra("categoria", viaje.categoria)
                                    startActivity(intento)
                                }
                            }
                        }
                    }

                    (categorias[position].toString() == "Pasados") -> {
                        val hoy = Date()
                        val chpGroup=findViewById<ChipGroup>(R.id.chpGroup)
                        chpGroup.removeAllViews()
                        for (viaje in viajeList) {
                            if (viaje.fecha.before(hoy)) {
                                val chip= Chip(this@MainActivity)
                                chip.text= viaje.nombre
                                chip.isClickable=true
                                chip.isCheckable=false
                                chip.isCloseIconVisible = true

                                chip.setOnCloseIconClickListener {
                                    chpGroup.removeView(chip)
                                    viajeList.remove(viaje)}
                                chpGroup.addView(chip as View)
                                chip.setOnClickListener{
                                    val intento = Intent(this@MainActivity, DetailActivity::class.java)
                                    intento.putExtra("nombre", viaje.nombre)
                                    intento.putExtra("destino", viaje.destino)
                                    intento.putExtra("fecha", SimpleDateFormat("dd/MM/yyyy").format(viaje.fecha))
                                    intento.putExtra("duracion", viaje.duracion.toString())
                                    intento.putExtra("categoria", viaje.categoria)
                                    startActivity(intento)
                                }
                            }
                        }
                    }
                }

                Toast.makeText(this@MainActivity, "Filtrando los viajes: " +categorias[position],
                    Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "No se seleccionó ningún filtro" ,
                    Toast.LENGTH_SHORT).show()
            }
        }

        btnAgregar.setOnClickListener{
            agregarChip(txtNombreViaje.text.toString(),txtDestino.text.toString(),convertirTextoAFecha(txtFecha.text.toString()),txtDuracion.text.toString().toInt())
        }

    }

    var viajeList= mutableListOf<Viaje>()

    fun agregarChip(nombre: String, destino:String, fecha: Date, duracion:Int){
        val chip= Chip(this@MainActivity)
        var categoria = chipSeleccionado()
        var viaje=Viaje(nombre, destino,fecha,duracion,categoria)
        chip.text= nombre
        chip.isClickable=true
        chip.isCheckable=false
        chip.isCloseIconVisible = true

        viajeList.add(viaje)

        val chpGroup=findViewById<ChipGroup>(R.id.chpGroup)

        chip.setOnCloseIconClickListener {
            chpGroup.removeView(chip)
            viajeList.remove(viaje)
        }
        chpGroup.addView(chip as View)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fechaFormatted = dateFormat.format(fecha)

        chip.setOnClickListener{
            val intento = Intent(this@MainActivity, DetailActivity::class.java)
            intento.putExtra("nombre", nombre)
            intento.putExtra("destino", destino)
            intento.putExtra("fecha", fechaFormatted)
            intento.putExtra("duracion", duracion.toString())
            intento.putExtra("categoria", categoria)
            startActivity(intento)
        }

    }

    fun chipSeleccionado(): String {
        val chpPlaya = findViewById<Chip>(R.id.chpPlaya)
        val chpAventura = findViewById<Chip>(R.id.chpAventura)
        val chpCultura = findViewById<Chip>(R.id.chpCultura)

        val selectedChips = mutableListOf<String>()

        if (chpPlaya.isChecked) {
            selectedChips.add(chpPlaya.text.toString())
        }
        if (chpAventura.isChecked) {
            selectedChips.add(chpAventura.text.toString())
        }
        if (chpCultura.isChecked) {
            selectedChips.add(chpCultura.text.toString())
        }

        return selectedChips.joinToString(", ")
    }

    fun convertirTextoAFecha(texto: String): Date {
        val formato = SimpleDateFormat("dd/MM/yyyy")
        return formato.parse(texto)
    }
}