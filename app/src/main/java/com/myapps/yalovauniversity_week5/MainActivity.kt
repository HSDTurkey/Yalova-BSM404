package com.myapps.yalovauniversity_week5

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.myapps.yalovauniversity_week5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // 3rd Party Library Example -- Upload Profile Picture with Glide
        binding.uploadProfileImage.setOnClickListener {
            Glide.with(this)
                .load("https://i.picsum.photos/id/10/300/300.jpg?hmac=-HNJRisuHIZRc8PHpxFmPyT6yP7T3SZ6puHalS_MgqQ")
                .centerCrop()
                .into(binding.profileImage)
        }

        // Listen Text Change For Password in Edit Text
        binding.profilePasswordTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                when (s?.length) {
                    5 -> {
                        binding.passwordSecurityLevel.visibility = GONE
                    }
                    6 -> {
                        binding.passwordSecurityLevel.visibility = VISIBLE
                        binding.passwordSecurityLevel.text = "Security Level: Low"
                        binding.passwordSecurityLevel.setTextColor(Color.RED)
                    }
                    7 -> {
                        binding.passwordSecurityLevel.text = "Security Level: Medium"
                        binding.passwordSecurityLevel.setTextColor(Color.YELLOW)
                    }
                    8 -> {
                        binding.passwordSecurityLevel.text = "Security Level: High"
                        binding.passwordSecurityLevel.setTextColor(Color.GREEN)
                    }
                    else -> {
                        binding.passwordSecurityLevel.text = "Security Level: High"
                        binding.passwordSecurityLevel.setTextColor(Color.GREEN)
                    }
                }
            }
        })

        // Button Click -- Update Profile
        binding.updateUser.setOnClickListener {

            // Check email address -- Validation
            if (binding.profileEmailOrPhoneTxt.text.toString().isValidEmail()) {
                // Check password length
                if (binding.profilePasswordTxt.text.length >= 6) {
                    // Check Agreements Checkbox
                    if (binding.agreementCheckbox.isChecked) {

                        // Get Text from Edit Text and Set Text to Text View
                        binding.profileFullName.text = binding.profileFullNameTxt.text

                        // Radio Button Control
                        if (binding.radioButtonMale.isChecked) {
                            binding.profileUserGender.text = "Gender: Male"
                        } else {
                            binding.profileUserGender.text = "Gender: Female"
                        }

                        Toast.makeText(this, "Updated Profile Information.", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, "Please check our agreements.", Toast.LENGTH_LONG)
                            .show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Password should contain 6 or above characters.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(this, "Please check email address", Toast.LENGTH_LONG).show()
            }

        }

        setContentView(binding.root)
    }

    // String Extension Function for Email Validation
    private fun String.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}