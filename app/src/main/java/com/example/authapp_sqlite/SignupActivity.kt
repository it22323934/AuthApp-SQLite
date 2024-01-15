package com.example.authapp_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.authapp_sqlite.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper=DatabaseHelper(this)

        binding.signupButton.setOnClickListener {
            val signupName=binding.signupUserName.text.toString()
            val signupPassword=binding.signupUserPassword.text.toString()
            signupDatabase(signupName,signupPassword)
        }

        binding.loginRedirect.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun signupDatabase(username:String,password:String){
        val insertRowId=databaseHelper.insertUser(username,password)
        if(insertRowId!=-1L){
            Toast.makeText(this,"Signup Successful",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Signup failed",Toast.LENGTH_SHORT).show()
        }
    }

}