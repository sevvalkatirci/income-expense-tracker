package com.sevval.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.sevval.finalproject.Screens.MainActivity
import com.sevval.finalproject.model.User
import com.sevval.finalproject.viewmodel.UserViewModel
import com.sevval.finalproject.viewmodel.UserViewModelFactory

class RegisterScreen() : AppCompatActivity() {

    lateinit var user_name:String
    lateinit var user_password:String

    lateinit var nameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var registerButton: Button
    lateinit var loginButton: Button


    private val userViewModel:UserViewModel by viewModels {
        UserViewModelFactory((application as TransactionApplication).userRepo)
    }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login_screen)

            nameEditText=findViewById(R.id.userNameET)
            passwordEditText=findViewById(R.id.userPasswordET)
            registerButton=findViewById(R.id.registerButton)
            loginButton=findViewById(R.id.loginButton)

            registerButton.setOnClickListener {
                if(!nameEditText.text.isEmpty()&&!passwordEditText.text.isEmpty()){
                    user_name=nameEditText.text.toString()
                    user_password=passwordEditText.text.toString()
                    var user=User(user_name,user_password)
                    userViewModel.insert(user)

                   userViewModel.allUserInfo.observe(this){users->
                        users.let {
                            //userViewModel.deleteAll(users)
                        }
                }
            }
            }
            loginButton.setOnClickListener {
                userViewModel.allUserInfo.observe(this){users->
                    users.let {
                        for (item in it){
                            if(item.userName.equals(nameEditText.text.toString())&&item.userPassword.equals(passwordEditText.text.toString())){
                                val intent= Intent(this@RegisterScreen, MainActivity::class.java)
                                startActivity(intent)
                                this.finish()
                            }
                        }
                    }
                }
               }
            }
        }

