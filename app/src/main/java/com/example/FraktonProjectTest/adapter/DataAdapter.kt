package com.example.FraktonProjectTest.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.FraktonProjectTest.R
import com.example.FraktonProjectTest.activity.ProfileActivity
import com.example.FraktonProjectTest.model.Data

class DataAdapter(private var data: ArrayList<Data>, private var context: Context): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    var index:Int = -1
//    var id:Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.data_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dataview = data.get(position)
        holder.name?.setText(dataview.first_name)
        holder.surname?.setText(dataview.last_name)
        holder.email?.setText(dataview.email)
        var id = dataview.id

        Glide.with(context)
                .load(dataview.avatar)
                .into(holder.img)

        holder.layout?.setOnClickListener {
            index = position
            notifyDataSetChanged()
        }
        if (index == position){
            holder.layout?.setBackgroundColor(Color.parseColor("#f2f6fc"))
        }
        else {
            holder.layout?.setBackgroundColor(Color.parseColor("#ffffff"))
        }
        holder.layout?.setOnClickListener {
            var intent = Intent(context, ProfileActivity::class.java)
//            var number = DataInterface.getPositon(position)
            intent.putExtra("id", id)
            intent.putExtra("size", data.size)
            context.startActivity(intent)
        }
    }
    fun setAdapterData(data: ArrayList<Data>){
        this.data = data
        notifyDataSetChanged()
    }
    class ViewHolder : RecyclerView.ViewHolder {
        var name:TextView? = null
        var surname:TextView? = null
        var email:TextView? = null
        var img:ImageView? = null
        var layout:LinearLayout? = null

        constructor(view: View) : super(view) {
            this.name = view.findViewById(R.id.txtName)
            this.surname = view.findViewById(R.id.txtSurname)
            this.email = view.findViewById(R.id.txtEmail)
            this.img = view.findViewById(R.id.img)
            this.layout = view.findViewById(R.id.layout)
        }
    }
}