package com.sayson.narl.imessage

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    var logAuth: FirebaseAuth? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        logAuth = FirebaseAuth.getInstance()
        loginButtonId.setOnClickListener{
            var email = loginEmail.text.toString().trim()
            var password = loginPassword.text.toString().trim()

            if(!TextUtils.isEmpty(email)|| !TextUtils.isEmpty(password)){

            }else{
                Toast.makeText(this,"Error Logging In",Toast.LENGTH_LONG)
                        .show()
            }
            loginUser(email,password)
        }

    private fun loginUser(email: String, password: String) {
        logAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    task: Task<AuthResult> ->
                    if (task.isSuccessful){

                        var username = email.split("@")[0]
                        var contactsIntent = Intent(this, HomeActivity::class.java)
                        contactsIntent.putExtra("name", username)
                        startActivity(contactsIntent)
                        finish()

                    }else{
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG)
                                .show()

                    }
                }

    }
}

