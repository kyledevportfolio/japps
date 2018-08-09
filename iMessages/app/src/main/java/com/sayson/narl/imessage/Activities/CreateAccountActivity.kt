package com.sayson.narl.imessage.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.sayson.narl.imessage.R
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null
    lateinit var db: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mAuth = FirebaseAuth.getInstance()




        createAccount.setOnClickListener {
            var username = userNameEt.text.toString().trim()
            var email = emailEt.text.toString().trim()
            var phoneNumber = contactEt.text.toString().trim()
            var password = passWordEt.text.toString().trim()
            var confirmPassword = confirmPasswordEt.text.toString().trim()


            if (!TextUtils.isEmpty(username) || !TextUtils.isEmpty(email)
                    || !TextUtils.isEmpty(phoneNumber)||TextUtils.isEmpty(password)||TextUtils.isEmpty(confirmPassword)) {
                createAccount(username,email,phoneNumber,password/*,confirmPassword*/)
            }else {
                Toast.makeText(this, "Please fill out the fields", Toast.LENGTH_LONG)
                        .show()
            }

        }
    }

    fun createAccount( username: String, Email: String,phoneNumber:String, password:String/*,confirmPassword:String*/) {
        mAuth!!.createUserWithEmailAndPassword ( Email,password)
                .addOnCompleteListener{
                    task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        var currentUser = mAuth!!.currentUser
                        var userId = currentUser!!.uid

                        mDatabase = FirebaseDatabase.getInstance().reference
                                .child("Contacts").child(userId)



                        var userObject = HashMap<String, String>()
                        userObject.put("username", username)
                        userObject.put("email", Email)
                        userObject.put("phoneNumber", phoneNumber)


                        mDatabase!!.setValue(userObject).addOnCompleteListener {
                            task: Task<Void> ->
                            if (task.isSuccessful) {
                                var contactsIntent = Intent(this, HomeActivity::class.java)
                                contactsIntent.putExtra("name", username)
                                startActivity(contactsIntent)
                                finish()

                            }else {

                                Toast.makeText(this, "User Not Created!", Toast.LENGTH_LONG)
                                        .show()

                            }
                        }







                    }else {

                    }


                }


    }
}