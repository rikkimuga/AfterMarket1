package com.kuria.aftermarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var editTextEmail:EditText? = null
    var editTextPassword:EditText? = null
    var registerButton:Button? = null
    var tvLogin:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextEmail = findViewById(R.id.edt_Email1)
        editTextPassword = findViewById(R.id.edt_Password1)
        registerButton = findViewById(R.id.btn_register)
        tvLogin = findViewById(R.id.tv_Login)
        tvLogin!!.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }


        registerButton!!.setOnClickListener {
            when{
                TextUtils.isEmpty(editTextEmail!!.text.toString().trim{it <=' '}) ->{
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editTextPassword!!.text.toString().trim {it <=' '})->{
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = editTextEmail!!.text.toString().trim{it <= ' '}
                    val password: String = editTextPassword!!.text.toString().trim{it <= ' '}

                    //Create an Instance and create a register a user with email and password.


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->

                            //If the registration is successfully done
                            if (task.isSuccessful){

                                //Firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@MainActivity,
                                    "You have registered successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val  intent =
                                    Intent(this@MainActivity, Login::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id",firebaseUser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }else{

                            //If the registering is not successful then show error message.
                            Toast.makeText(
                                this@MainActivity,
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