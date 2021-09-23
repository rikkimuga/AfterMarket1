package com.kuria.aftermarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.FirebaseUser
//import kotlinx.android.synthetic.main.activity_login.*
class Login :AppCompatActivity() {
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var loginButton: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextEmail = findViewById(R.id.edt_Email1)
        editTextPassword = findViewById(R.id.edt_Password1)
        loginButton = findViewById(R.id.btn_Login)



        loginButton!!.setOnClickListener {
            when {
                TextUtils.isEmpty(editTextEmail!!.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Login,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editTextPassword!!.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Login,
                        "Please enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = editTextEmail!!.text.toString().trim { it <= ' ' }
                    val password: String = editTextPassword!!.text.toString().trim { it <= ' ' }

                    //Create an Instance and create a register a user with email and password.
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            //If the registration is successfully done
                            if (task.isSuccessful) {

                                //Firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@Login,
                                    "You have logged in successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent =
                                    Intent(this@Login, HomeActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {

                                //If the registering is not successful then show error message.
                                Toast.makeText(
                                    this@Login,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }
}






