package com.example.retrofi2.model

import com.google.gson.annotations.SerializedName

class DataClass {
        @SerializedName("first_name")
        var first_name:String? = null
        @SerializedName("last_name")
        var last_name:String? = null
        @SerializedName("email")
        var email:String? = null
        @SerializedName("avatar")
        var avatar:String? = null
}