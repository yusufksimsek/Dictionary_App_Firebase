package com.example.sozluk_uygulamasi

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Kelimeler(
    var kelime_id:String?="",
    var ingilizce:String?="",
    var turkce:String?="") : Serializable {

    }