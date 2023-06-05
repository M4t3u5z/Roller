package com.example.roller

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {

    //Zadeklaruj instancję FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //W metodzie onCreate() zainicjuj instancję FirebaseAuth
        auth = Firebase.auth

        val backToLoginText: TextView = findViewById(R.id.textView_back_to_login)

        backToLoginText.setOnClickListener {
            val intent = Intent(this, Logging::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.button_register)

        registerButton.setOnClickListener {
            // metoda pobierająca email i hasło od urzytkownika
            performSingUp()
        }

    }

    private fun performSingUp() {
        val email = findViewById<EditText>(R.id.editText_Email_register)
        val pasword = findViewById<EditText>(R.id.editText_Password_register)

        if (email.text.isEmpty() || pasword.text.isEmpty()){
            Toast.makeText(this, "Proszę wypełnić wszystkie pola rejestracji!", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = pasword.text.toString()

        //zarejestruj nowych urzytkowników
        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Udane logowanie, aktualizacja UI z informacjami o zalogowanym użytkowniku

                    val intent = Intent(this, Logging::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Brawo konto zostało stworzone.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // Jeśli logowanie nie powiedzie się, wyświetl komunikat dla użytkownika.
                    Toast.makeText(baseContext, "Niepowodzenie uwierzytelnienia.",
                        Toast.LENGTH_SHORT).show()
                }
            }
                //Wyświetlenie błędu
            .addOnFailureListener {
                Toast.makeText(this, "Wystąpił Błąd!! ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }


}