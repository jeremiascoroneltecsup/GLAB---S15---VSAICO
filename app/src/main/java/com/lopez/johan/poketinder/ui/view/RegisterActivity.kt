package com.lopez.johan.poketinder.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lopez.johan.poketinder.ui.viewmodel.RegisterViewModel
import com.lopez.johan.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el ViewModel
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        // Observa los valores de LiveData
        observeViewModel()

        // Configura los listeners de los botones
        setupButtonListeners()

    }

    private fun observeViewModel() {
        registerViewModel.emailError.observe(this) {
            Toast.makeText(this, "Formato de correo incorrecto", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordMismatchError.observe(this) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.registerSuccess.observe(this) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            finish()  // Cierra la actividad de registro después de un registro exitoso
        }
    }

    private fun setupButtonListeners() {
        // Botón de regreso
        binding.btnBackClose.setOnClickListener {
            finish()  // Cierra la actividad actual para regresar
        }

        // Botón "Ya tengo cuenta"
        binding.btnYaTengoCuenta.setOnClickListener {
            finish()  // Opcional, cierra esta actividad y vuelve al login
        }

        // Botón "Registrar"
        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtPassword2.text.toString()

            registerViewModel.registerUser(email, password, confirmPassword)
        }
    }
}
