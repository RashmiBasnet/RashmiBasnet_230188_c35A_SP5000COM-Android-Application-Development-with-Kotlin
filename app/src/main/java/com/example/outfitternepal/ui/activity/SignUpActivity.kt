package com.example.outfitternepal.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.outfitternepal.databinding.ActivitySignUpBinding
import com.example.outfitternepal.model.UserModel
import com.example.outfitternepal.repository.UserRepositoryImplementation

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val userRepository = UserRepositoryImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle signup button click
        binding.signUpButton.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val email = binding.emailInput.text.toString()
            val gender = findViewById<RadioButton>(binding.genderRadioGroup.checkedRadioButtonId)?.text.toString()
            val contact = binding.contactInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val confirmPassword = binding.confirmPasswordInput.text.toString()

            if (name.isEmpty() || email.isEmpty() || gender.isEmpty() || contact.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                // Call the signUp method from the repository
                userRepository.signUp(email, password) { isSuccess, message, userId ->
                    if (isSuccess) {
                        // Create a UserModel object
                        val userModel = UserModel(
                            userId = userId,
                            firstName = name,
                            lastName = "", // You can add a last name field if needed
                            address = "", // You can add an address field if needed
                            phoneNumber = contact,
                            Email = email
                        )

                        // Add the user to the database
                        userRepository.addUserToDatabase(userId, userModel) { isDatabaseSuccess, databaseMessage ->
                            if (isDatabaseSuccess) {
                                Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                                // Redirect to login screen
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, "Failed to add user to database: $databaseMessage", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Signup failed: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}