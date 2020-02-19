package com.example.retrofi2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ServiceGenerator
import com.example.retrofi2.R
import com.example.retrofi2.WebService.URLEndpoints
import com.example.retrofi2.adapter.DataAdapter
import com.example.retrofi2.model.ApiInterface
import com.example.retrofi2.model.Data
import com.example.retrofi2.model.UserData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    var data = ArrayList<Data>()
    var recyclerView: RecyclerView? = null
    var dataAdapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv) as RecyclerView

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        dataAdapter = DataAdapter(data, this)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = dataAdapter

        getData()

        txtCreateUser.setOnClickListener {
            val intent = Intent(this,CreateActivity::class.java)
            startActivity(intent)
        }

    }

    fun getData(){
        val apiInterface = ServiceGenerator.call(ApiInterface::class.java)
        val call: Call<UserData> = apiInterface.getData(URLEndpoints.basicLogin())
        call.enqueue(object: Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful())
                {
                    dataAdapter?.setAdapterData(response.body()?.data as ArrayList<Data>)
                    dataAdapter?.notifyDataSetChanged()
                }
                else
                {
                    Log.e(TAG, response.message() + "${response.code()}")
                }
            }
            override fun onFailure(call: Call<UserData>, t:Throwable?) {
                Log.e(TAG, t?.message)
            }
        })
    }

}
