package com.example.testalarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testalarapp.databinding.ActivityBaseBinding
import com.example.testalarapp.databinding.ActivityDetailInfoBinding

class DetailInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}