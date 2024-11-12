package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.FirebaseAuthService.auth
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    lateinit var btnentrar : Button
    lateinit var email : EditText
    lateinit var senha : EditText


    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        FirebaseApp.initializeApp(this)
        var btnres : Button = findViewById(R.id.btnRess)
        email = findViewById(R.id.emaillog)
        senha = findViewById(R.id.senhalog)

        btnres.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
        }


        btnentrar = findViewById(R.id.btnentrar)
        btnentrar.setOnClickListener {
            try {
                var email = email.text.toString()
                var senha = senha.text.toString()
                auth.signInWithEmailAndPassword(email, senha).addOnSuccessListener {
                    startActivity(Intent(this, TelaPrinc::class.java).putExtra("email", email))
                }.addOnFailureListener {
                    Toast.makeText(this, "erro ao fazer login", Toast.LENGTH_SHORT).show()
                }
            } catch (exeption: Exception) {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show()
            }


        }



    }



}