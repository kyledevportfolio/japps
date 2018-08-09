package com.sayson.narl.imessage.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.DocumentReference
import com.sayson.narl.imessage.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null
    lateinit var db: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        loginButtonId.setOnClickListener{
            var email = loginEmail.text.toString().trim()
            var password = loginPassword.text.toString().trim()

            if(!TextUtils.isEmpty(email)|| !TextUtils.isEmpty(password)){

                loginUser(email,password)
            }else{
                Toast.makeText(this, "Sorry, login failed!", Toast.LENGTH_LONG)
                        .show()
            }


        }
        createAccount.setOnClickListener{
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    task: Task<AuthResult> ->
                    if (task.isSuccessful){

                        var username = email.split("@")[0]
                        var missionboardIntent = Intent(this, HomeActivity::class.java)
                        missionboardIntent.putExtra("name", username)
                        startActivity(missionboardIntent)
                        finish()

                    }else{
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG)
                                .show()

                    }
                }

    }
}
