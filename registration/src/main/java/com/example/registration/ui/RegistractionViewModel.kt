package com.example.registration.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.data.model.RegistroData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistractionViewModel @Inject constructor() : ViewModel() {

    private val _recordData = MutableLiveData(RegistroData())
    val recordData: LiveData<RegistroData> = _recordData

    fun updatePersonalData(
        name: String,
        age: String,
        birthdate: String,
        typeDocument: String,
        documentNumber: String,
        neighborhood: String,
        phoneNumber: String
    ) {
        _recordData.value = _recordData.value?.copy(
            name = name,
            age = age,
            birthdate = birthdate,
            typeDocument = typeDocument,
            documentNumber = documentNumber,
            neighborhood = neighborhood,
            phoneNumber = phoneNumber
        )
    }
}