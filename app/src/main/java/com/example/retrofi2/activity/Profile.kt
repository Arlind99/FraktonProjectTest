package com.example.retrofi2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.recyclerview.ServiceGenerator
import com.example.retrofi2.R
import com.example.retrofi2.model.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {

    private val TAG = "Profile"
    private var url = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()
    }

    fun init(){
        url= intent.getIntExtra("id", 0)
        getData()

    }

    fun getData(){
        val apiInterface = ServiceGenerator.call(ApiInterface::class.java)
        var call: Call<DetailData> = apiInterface.getPersonalData("users/"+url)
        call.enqueue(object: Callback<DetailData> {
            override fun onResponse(call: Call<DetailData>, response: Response<DetailData>) {
                if (response.isSuccessful())
                {
                    var profile = response.body()?.data
                    txtNameSurname.setText(profile?.first_name + profile?.last_name)
                    txtEmail.setText(profile?.email)
                    Glide.with(this@Profile)
                        .load(profile?.avatar)
                        .into(imgProfile)
                }
                else
                {
                    Log.e(TAG, response.message() + "${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailData>, t: Throwable) {
                Log.e(TAG, t.message)
            }

        })
    }

}
