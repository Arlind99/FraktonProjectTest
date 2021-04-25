package com.example.FraktonProjectTest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.FraktonProjectTest.R
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        btnCreate.setOnClickListener {
            Toast.makeText(this,txtInName.text.toString()+" has been added to database",Toast.LENGTH_LONG).show()
        }

    }
}
