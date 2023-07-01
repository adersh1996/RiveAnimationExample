package com.project.riveanimationexample

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import app.rive.runtime.kotlin.core.Rive
import com.project.riveanimationexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val stateMachineName = "Login Machine"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Rive.init(this)

        binding.emailEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.loginCharacterAnimation.controller.setBooleanState(
                    stateMachineName,
                    "isChecking",
                    true
                )
            } else {
                binding.loginCharacterAnimation.controller.setBooleanState(
                    stateMachineName,
                    "isChecking",
                    false
                )
            }
        }
        binding.passwordEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.loginCharacterAnimation.controller.setBooleanState(
                    stateMachineName,
                    "isHandsUp",
                    true
                )
            } else {
                binding.loginCharacterAnimation.controller.setBooleanState(
                    stateMachineName,
                    "isHandsUp",
                    false
                )
            }
        }
        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    binding.loginCharacterAnimation.controller.setNumberState(
                        stateMachineName,
                        "numLook",
                        s!!.length.toFloat()
                    )
                } catch (e: Exception) {
                    TODO("Not yet implemented")
                }
            }

        })
        binding.button.setOnClickListener{

            binding.passwordEt.clearFocus()

            Handler(mainLooper).postDelayed({
                if (binding.emailEt.text!!.isNotEmpty()||binding.passwordEt.text!!.isNotEmpty()&&
                    (binding.emailEt.text.toString().equals("admin@gmail.com")&&binding.passwordEt.text.toString().equals("123456"))){
                    binding.loginCharacterAnimation.controller.fireState(stateMachineName, "trigSuccess")
                }else{
                    binding.loginCharacterAnimation.controller.fireState(stateMachineName, "trigFail")
                }
            },2000)

        }
    }
}