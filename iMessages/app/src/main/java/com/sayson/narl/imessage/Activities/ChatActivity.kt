package com.sayson.narl.imessage.Activities


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.sayson.narl.imessage.Adapters.MessageAdapter
import com.sayson.narl.imessage.R
import com.google.firebase.firestore.*
import com.sayson.narl.imessage.mods.ThisMessage
import com.sayson.narl.imessage.mods.time
import com.sayson.narl.imessage.mods.userAcc
import kotlinx.android.synthetic.main.messaging.*

class ChatActivity : AppCompatActivity() {
    var arrofMessages: ArrayList<ThisMessage> = ArrayList()
    var adapter: MessageAdapter? = null
    var account = userAcc()
    var id = ""
    var myemail = ""
    var email = ""
    var name = ""
    var thisid = ""
    var nameview: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.messaging)
        val iin = intent
        var bool = true
        var b: Bundle = iin.extras
        email = b.getString("email")
        myemail = b.getString("myemail")
        thisid=b.getString("thisid")
        id = b.getString("id")
        name = b.getString("name")
        var db = FirebaseFirestore.getInstance()
        nameview = findViewById(R.id.UserName)

        nameview!!.text = name
        Toast.makeText(applicationContext, email + "+" + myemail, Toast.LENGTH_LONG).show()


        db = FirebaseFirestore.getInstance()
        db.collection("Session")
                .document(email + "+" + myemail)
                .collection("Message")
                .orderBy("TimeStamp", Query.Direction.ASCENDING)

                .addSnapshotListener(object : EventListener<QuerySnapshot> {


                    override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                        if (p1 != null) {
                            Log.w("ERROR", "Listen failed.", p1)
                            return


                        }
                        if (arrofMessages.isNotEmpty()) {
                            arrofMessages.clear()
                        }
                        for (doc in p0!!.documents) {
                            .LENGTH_LONG).show()


                            var user = doc.toObject(ThisMessage::class.java)
                            // ADAPTER HERE
                            user.myemail = myemail
                            arrofMessages.add(user)
                            adapter = MessageAdapter(arrofMessages, applicationContext)
                            adapter!!.notifyDataSetChanged()

                        }

                        var layout_manager = LinearLayoutManager(applicationContext)
                        viewR.layoutManager = layout_manager
                        viewR.recycledView//Toast.makeText(applicationContext, doc.get("contact").toString(),ToastPool.setMaxRecycledViews(0,0)
                        viewR.setHasFixedSize(true)
                        viewR.adapter = adapter }
                }
                )




        Sendbutton.setOnClickListener {
            var mbox: HashMap<String, Any>? = HashMap()
            var message: ArrayList<ThisMessage> = ArrayList()
            mbox!!.put("Messages", editText!!.text.toString())
            mbox!!.put("Sender", myemail)
            mbox!!.put("SenderName",name)
            mbox!!.put("Reciever", email)
            mbox!!.put("TimeStamp", time().getCurrentTime())

            var sendb = FirebaseFirestore.getInstance()

            sendb.collection("Session").document(myemail + "+" + email).collection("Message").add(mbox)
            sendb.collection("Session").document(email + "+" + myemail).collection("Message").add(mbox)
            sendb.collection("Users").document(id).collection("Inbox").add(mbox)
            sendb.collection("Users").document(thisid).collection("Inbox").add(mbox)



            editText.text.clear()


        }


    }


}