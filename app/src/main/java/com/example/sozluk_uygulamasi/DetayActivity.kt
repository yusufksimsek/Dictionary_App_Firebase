package com.example.sozluk_uygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sozluk_uygulamasi.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        binding.textViewIngilizce.text = kelime.ingilizce
        binding.textViewTurkce.text = kelime.turkce

    }
}