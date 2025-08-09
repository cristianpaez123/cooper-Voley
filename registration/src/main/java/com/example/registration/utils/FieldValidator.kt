package com.example.registration.utils

import java.text.SimpleDateFormat
import java.util.*

object FieldValidator {

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isRequired(text: String): Boolean {
        return text.trim().isNotEmpty()
    }

    fun isValidBirthday(dateText: String): Boolean {
        if (dateText.trim().isEmpty()) return false

        return try {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val selectedDate = dateFormat.parse(dateText)
            val today = Date()

            selectedDate != null && !selectedDate.after(today)
        } catch (e: Exception) {
            false
        }
    }
}