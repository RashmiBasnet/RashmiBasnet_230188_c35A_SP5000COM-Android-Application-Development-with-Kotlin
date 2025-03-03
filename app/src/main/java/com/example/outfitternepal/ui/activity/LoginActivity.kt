package com.example.outfitternepal.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.outfitternepal.databinding.ActivityLoginBinding
import com.example.outfitternepal.repository.UserRepositoryImplementation

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val userRepository = UserRepositoryImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle login button click
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password are required!", Toast.LENGTH_SHORT).show()
            } else {
                // Call the login method from the repository
                userRepository.login(email, password) { isSuccess, message ->
                    if (isSuccess) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        // Redirect to home screen
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish() // Close the login activity to prevent going back
                    } else {
                        Toast.makeText(this, "Login failed: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Handle signup text click (optional)
        binding.signUpText.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // Handle forgot password text click (optional)
        binding.forgotPasswordText.setOnClickListener {
            val email = binding.emailInput.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email to reset password.", Toast.LENGTH_SHORT).show()
            } else {
                userRepository.forgetPassword(email) { isSuccess, message ->
                    if (isSuccess) {
                        Toast.makeText(this, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to send reset email: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}