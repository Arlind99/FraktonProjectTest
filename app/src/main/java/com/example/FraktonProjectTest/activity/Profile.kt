package com.example.FraktonProjectTest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.recyclerview.ServiceGenerator
import com.example.FraktonProjectTest.R
import com.example.FraktonProjectTest.WebService.ApiInterface
import com.example.FraktonProjectTest.model.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {

    private val TAG = "Profile"
    private var url = 0
    private var dataSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()

        btnNext.setOnClickListener {
            if (url < dataSize)
                getData(++url)
        }

        btnPreview.setOnClickListener {
            if (url > 1)
                getData(--url)
        }
    }

    fun init(){
        url = intent.getIntExtra("id", 0)
        dataSize = intent.getIntExtra("size", 0)
        getData(url)

    }

    fun getData(ID:Int){
        val apiInterface = ServiceGenerator.call(ApiInterface::class.java)//https://reqres.in/api/users/{userId}.
        var call: Call<DetailData> = apiInterface.getPersonalData("users/"+ID)
        call.enqueue(object: Callback<DetailData> {
            override fun onResponse(call: Call<DetailData>, response: Response<DetailData>) {
                if (response.isSuccessful())
                {
                    var profile = response.body()?.data
                    txtID.text = profile?.id.toString()
                    txtNameSurname.text = profile?.first_name + " " + profile?.last_name
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
