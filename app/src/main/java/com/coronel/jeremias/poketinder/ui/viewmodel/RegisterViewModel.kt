package com.coronel.jeremias.poketinder.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coronel.jeremias.poketinder.data.database.SharedPreferencesRepository

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    val emailError = MutableLiveData<Boolean>()
    val passwordMismatchError = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(application.applicationContext)
        }

    // Valida el formato del email
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Valida que las contraseñas coincidan
    fun isPasswordMatching(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    // Registra el usuario si los datos son válidos
    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (!isEmailValid(email)) {
            emailError.postValue(true)
            return
        }

        if (!isPasswordMatching(password, confirmPassword)) {
            passwordMismatchError.postValue(true)
            return
        }

        // Guarda email y contraseña en SharedPreferences
        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)

        // Publica que el registro fue exitoso
        registerSuccess.postValue(true)
    }
}
