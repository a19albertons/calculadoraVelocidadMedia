package com.example.calculadoravelocidadmedia

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.calculadoravelocidadmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    val model: CalculadoraModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.cantidadKm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                model.kilometros.value = s?.toString() ?: "0"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        binding.cantidadHoras.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                model.horas.value = s?.toString() ?: "0"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        val observer = Observer<String> {
            val km = model.kilometros.value?.toIntOrNull() ?: 0
            val h = model.horas.value?.toIntOrNull() ?: 0
            if (h!=0) {
                val kmPorHora = km/h
                val mPorSegundo = (km*1000)/(h*3600)
                binding.kilometrosHora.text = kmPorHora.toString()
                binding.metrosSegundo.text = mPorSegundo.toString()
                binding.mensajeError.text = getString(R.string.error_message)
            }
            else {
                binding.kilometrosHora.text = "0"
                binding.metrosSegundo.text = "0"
                binding.mensajeError.text = getString(R.string.error_message_alt)
                //Toast.makeText(this, "Esta feo eso de indicar 0 horas o nada", Toast.LENGTH_LONG).show()

            }
        }
        model.kilometros.observe(this, observer)
        model.horas.observe(this, observer)

    }
}