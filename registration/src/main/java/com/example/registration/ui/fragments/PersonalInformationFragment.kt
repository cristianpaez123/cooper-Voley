package com.example.registration.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.registration.R
import com.example.registration.databinding.FragmentPersonalInformationBinding
import com.example.registration.ui.RegistractionViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class PersonalInformationFragment : Fragment() {

    private lateinit var binding: FragmentPersonalInformationBinding
    private val viewModel: RegistractionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupDatePicker()
        setupDocumentTypeSpinner()
        setupNextButton()
        observeViewModel()
    }

    private fun setupDatePicker() {
        binding.editBirthday.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecciona tu fecha de nacimiento")
                .build()

            datePicker.show(parentFragmentManager, "date_picker")
            datePicker.addOnPositiveButtonClickListener { dateMillis ->
                val formattedDate =
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(dateMillis))
                binding.editBirthday.setText(formattedDate)
            }
        }
    }

    private fun setupDocumentTypeSpinner() {
        val items = resources.getStringArray(R.array.document_types)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        binding.documentTypeDropdown.setAdapter(adapter)
    }

    private fun setupNextButton() {
        binding.buttonNext.setOnClickListener {
            val name = binding.editName.text.toString()
            val age = binding.editAge.text.toString()
            val birthdate = binding.editBirthday.text.toString()
            val documentNumber = binding.editDocumentNumber.text.toString()
            val typeDocument = getSelectedDocumentType()
            val neighborhood = binding.editNeighborhood.text.toString()
            val phoneNumber = binding.editPhoneNumber.text.toString()

            viewModel.updatePersonalData(name,age,birthdate,documentNumber,typeDocument,neighborhood,phoneNumber)
        }
    }

    private fun getSelectedDocumentType(): String {
        return binding.documentTypeDropdown.text.toString()
    }

    private fun observeViewModel() {
        viewModel.recordData.observe(viewLifecycleOwner) { data ->
            Log.d("RegistroData", "Datos actualizados: $data")
        }
    }
}