package com.example.testalarapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testalarapp.database.DataOfDataBase
import com.example.testalarapp.databinding.ActivityBaseBinding
import com.example.testalarapp.databinding.ActivityDetailInfoBinding
import okhttp3.internal.notifyAll

class DetailInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val temp = intent.getSerializableExtra("info") as DataOfDataBase?

        binding.textView3.text = temp?.id
        binding.textView4.text = temp?.name
        binding.textView5.text = temp?.country
        binding.textView6.text = temp?.lat?.toString()
        binding.textView7.text = temp?.lon?.toString()

        binding.button.setOnClickListener {
            val intent = Intent(this, ActivityBase::class.java)
            finish()
        }

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:${temp?.lat},${temp?.lon}?q=${temp?.lat},${temp?.lon}(name)"))
            intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
        }



    }

    fun returnBack(view: View) {

    }
}