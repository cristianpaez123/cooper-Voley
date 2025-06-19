package com.example.coopervoley.ui.login

import androidx.lifecycle.ViewModel
import com.example.coopervoley.R
import com.example.coopervoley.domain.UseCase.LoginUseCase
import com.example.coopervoley.domain.model.LoginResult
import com.example.coopervoley.utils.CredentialValidator
import com.example.mobileappproductsearch.ui.common.BaseViewModel
import com.example.mobileappproductsearch.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Initial)
    val uiState: StateFlow<UiState<Unit>> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        if (!isInputValid(email, password)) return

        _uiState.value = UiState.Loading

        launch(onError = ::handleLoginError) {
            val result = loginUseCase(email, password)
            handleLoginResult(result)
        }
    }

    private fun isInputValid(email: String, password: String): Boolean {
        val errorResId = CredentialValidator.validate(email, password)
        return if (errorResId != null) {
            _uiState.value = UiState.Error.MessageRes(errorResId)
            false
        } else {
            true
        }
    }

    private fun handleLoginResult(result: LoginResult) {
        _uiState.value = when (result) {
            is LoginResult.Success -> UiState.Success(Unit)
            is LoginResult.Failure -> UiState.Error.MessageRes(result.errorResId)
        }
    }

    private fun handleLoginError(throwable: Throwable) {
        val messageUiState = throwable.message?.let {
            UiState.Error.MessageText(it)
        } ?: UiState.Error.MessageRes(R.string.error_unexpected)

        _uiState.value = messageUiState
    }
}
