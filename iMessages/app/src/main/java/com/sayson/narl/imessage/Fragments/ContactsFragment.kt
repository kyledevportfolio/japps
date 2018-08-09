package com.sayson.narl.imessage.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sayson.narl.imessage.Adapters.ContactsAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.*
import com.sayson.narl.imessage.R
import com.sayson.narl.imessage.mods.Contacts
import com.sayson.narl.imessage.Adapters.AdapterHere

import io.grpc.Context
import kotlinx.android.synthetic.main.fragment_contacts.*
var id: Int=0
var name = ""
var email = ""
var contacts: ArrayList<Contacts> = ArrayList()
var adapter: AdapterHere? = null
class ContactsFragment : Fragment() {

    var mUserDatabase: DatabaseReference? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_contacts,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

        mUserDatabase = FirebaseDatabase.getInstance().reference.child("Contacts")


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val dbse = FirebaseFirestore.getInstance()
        dbse.collection("Users")
                .orderBy("Username", Query.Direction.DESCENDING)
                .addSnapshotListener(object : EventListener<QuerySnapshot> {

                    override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                        if (p1 != null) {
                            Log.w("ERROR", "Listen failed.", p1)
                            return
                        }
                        for (doc in p0!!.documents) {
                            if (doc.getString("Email") != email) {
                                var user = doc.toObject(Contacts::class.java)

                                user.id = id
                                user.email = email
                                user.username = name
                                user.id = doc.id
                                contacts.add(user!!)
                                adapter = AdapterHere(contacts, activity!!.applicationContext)
                                var layout_manager = LinearLayoutManager(activity!!.applicationContext)
                                layout_manager.reverseLayout = false
                                contactRecyclerView.layoutManager = layout_manager
                                contactRecyclerView.setHasFixedSize(true)

                                contactRecyclerView.adapter = adapter

                                adapter!!.notifyDataSetChanged()
                            }
                        }
                    }

                })





    }

}


