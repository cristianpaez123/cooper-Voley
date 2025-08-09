package com.example.myapplication.utils

import android.widget.AutoCompleteTextView
import android.widget.EditText
import com.example.registration.utils.FieldValidator
import com.google.android.material.textfield.TextInputLayout

object InputValidator {

    fun validateRequiredFields(vararg fields: EditText): Boolean {
        var isValid = true
        fields.forEach { field ->
            if (!FieldValidator.isRequired(field.text.toString())) {
                field.error = "Este campo es obligatorio"
                isValid = false
            } else {
                field.error = null
            }
        }
        return isValid
    }

    fun validateBirthday(birthdayField: EditText): Boolean {
        return if (!FieldValidator.isValidBirthday(birthdayField.text.toString())) {
            birthdayField.error = "Fecha inválida o en el futuro"
            false
        } else {
            birthdayField.error = null
            true
        }
    }

    fun validateDropdown(dropdown: AutoCompleteTextView, layout: TextInputLayout): Boolean {
        return if (!FieldValidator.isRequired(dropdown.text.toString())) {
            layout.error = "Este campo es obligatorio"
            false
        } else {
            layout.error = null
            true
        }
    }
}