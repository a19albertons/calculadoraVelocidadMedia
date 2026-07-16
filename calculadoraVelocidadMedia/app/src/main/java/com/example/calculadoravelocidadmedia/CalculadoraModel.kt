package com.example.calculadoravelocidadmedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculadoraModel : ViewModel() {
    val kilometros = MutableLiveData<String>("0")
    val horas = MutableLiveData<String>("0")
}