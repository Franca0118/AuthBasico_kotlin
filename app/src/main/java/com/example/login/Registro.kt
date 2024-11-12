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

class Registro : AppCompatActivity() {


    lateinit var emailres : EditText
    lateinit var senhalres : EditText
    lateinit var Btnres : Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailres = findViewById(R.id.emailres)
        senhalres = findViewById(R.id.senhares)
        Btnres = findViewById(R.id.btnres)
        FirebaseApp.initializeApp(this)

        Btnres.setOnClickListener {
            val email = emailres.text.toString()
            val senha = senhalres.text.toString()
            try {
                auth.createUserWithEmailAndPassword(email, senha).addOnFailureListener{
                    Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    Toast.makeText(this, "SUCESSO", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            } catch (exeption: Exception) {
                Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show()
            }

        }
    }
}