package com.kanyideveloper.realmdatabasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.kanyideveloper.realmdatabasedemo.databinding.ActivityAddNoteBinding
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.saveButton.setOnClickListener {
            if (binding.titleEditText.text.toString()
                    .isEmpty() || binding.descriptionEditText.text.toString().isEmpty()
            ) {
                return@setOnClickListener
            } else {
                viewModel.addNote(
                    binding.titleEditText.text.toString(),
                    binding.descriptionEditText.text.toString()
                )

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}