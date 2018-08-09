package com.sayson.narl.imessage.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.sayson.narl.imessage.R
import com.sayson.narl.imessage.mods.Contacts
import io.grpc.Context


class ContactsAdapter(databaseQuery: DatabaseReference, var context:Context)
    :FirebaseRecyclerAdapter<Contacts, ContactsAdapter.ViewHolder>(
        Contacts::class.java,
        R.layout.contact_row,
        ContactsAdapter.ViewHolder::class.java,
        databaseQuery

){
    override fun populateViewHolder(viewHolder: ContactsAdapter.ViewHolder?, contact: Contacts?, position: Int) {

        var contactId = getRef(position).key
        viewHolder!!.bindView(contact!!,context)

viewHolder.itemView.setOnClickListener{


}
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var userNameTxt: String? = null
        var emailTxt: String? = null
        var phoneNumber: String? = null


        fun bindView(contact: Contacts, context:Context){
            var userName = itemView.findViewById<TextView>(R.id.userNameTv)
            var email = itemView.findViewById<TextView>(R.id.emailTV)


            emailTxt = contact.email
            userNameTxt = contact.username

            email.text = contact.email
            userName.text = contact.username
        }


    }

}