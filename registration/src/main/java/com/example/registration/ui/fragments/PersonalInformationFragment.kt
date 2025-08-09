package com.example.registration.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.myapplication.utils.InputValidator
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
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                val formattedDate = sdf.format(Date(dateMillis))

                binding.editBirthday.setText(formattedDate)

                binding.editBirthday.tag = dateMillis
                binding.editBirthday.error = null
            }
        }
    }

    private fun setupDocumentTypeSpinner() {
        val items = resources.getStringArray(R.array.document_types)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        binding.documentTypeDropdown.setAdapter(adapter)
    }

    private fun setupNextButton() {
        binding.apply {
            buttonNext.setOnClickListener {
                val fieldsValid = InputValidator.validateRequiredFields(
                    binding.editName,
                    binding.editAge,
                    binding.editBirthday,
                    binding.editDocumentNumber,
                    binding.editNeighborhood,
                    binding.editPhoneNumber
                )

                val birthdayValid = InputValidator.validateBirthday(binding.editBirthday)
                val documentTypeValid = InputValidator.validateDropdown(
                    binding.documentTypeDropdown,
                    binding.tipoDocumentoLayout
                )
                if (fieldsValid && birthdayValid && documentTypeValid) {
                    viewModel.updatePersonalData(
                        binding.editName.text.toString().trim(),
                        binding.editAge.text.toString().trim(),
                        binding.editBirthday.text.toString().trim(),
                        binding.editDocumentNumber.text.toString().trim(),
                        binding.documentTypeDropdown.text.toString(),
                        binding.editNeighborhood.text.toString().trim(),
                        binding.editPhoneNumber.text.toString().trim()
                    )
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.recordData.observe(viewLifecycleOwner) { data ->
            Log.d("RegistroData", "Datos actualizados: $data")
        }
    }
}