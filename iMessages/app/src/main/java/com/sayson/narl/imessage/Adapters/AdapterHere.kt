package com.sayson.narl.imessage.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.sayson.narl.imessage.mods.Contacts
import com.sayson.narl.imessage.Activities.ChatActivity
class AdapterHere(list: ArrayList<Contacts>, context: Context) : RecyclerView.Adapter<AdapterHere.ContactHolder>() {
    var v: View? = null
    var data = list
    var mContext = context
    override fun onBindViewHolder(holder: ContactHolder, position: Int) {


        holder!!.email.text = data[position].email
        holder!!.fullname.text = data[position].username

        holder.click.setOnClickListener {
            var intent = Intent(mContext.applicationContext, ChatActivity::class.java)
            intent.putExtra("email",data[position].email)
            intent.putExtra("myemail",data[position].myemail)
            intent.putExtra("id",data[position].myid)
            intent.putExtra("thisid",data[position].userid)
            intent.putExtra("name",data[position].username)
            v!!.context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofcontact, parent, false)
        v = view

        return ContactHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setfilter(listitem: List<Contacts>) {
        data = ArrayList()
        data.addAll(listitem)
        notifyDataSetChanged()
    }


    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var fullname: TextView
        var email: TextView
        var click: LinearLayout

        init {

            click = itemView.findViewById(R.id.clickable)
            fullname = itemView.findViewById(R.id.Fullname)
            email = itemView.findViewById(R.id.email)
            image = itemView.findViewById(R.id.imageView)
        }

    }
}