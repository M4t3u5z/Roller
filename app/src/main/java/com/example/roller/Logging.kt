package com.example.roller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Logging : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)

        auth = Firebase.auth

        val registertext = findViewById<TextView>(R.id.textView_register)

        registertext.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            //metoda do logowania
            performLogin()
        }

    }

    private fun performLogin() {
        //wprowadzanie danych od urzytkownika

        val emailLogin = findViewById<EditText>(R.id.editText_Email_login)
        val paswordLogin = findViewById<EditText>(R.id.editText_Password_login)

        //warunek sprawdzający czy urzytkownik nie zostawił wolnego pola
        if (emailLogin.text.isEmpty() || paswordLogin.text.isEmpty()){
            Toast.makeText(this, "Wypełnij wszystkie pola logowania", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmailLogin = emailLogin.text.toString()
        val inputPaswordLogin = paswordLogin.text.toString()

        //logika logowania
        auth.signInWithEmailAndPassword(inputEmailLogin, inputPaswordLogin)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // jeśli logowanie się powiedzie przenieś do głównej akywności

                    val intent = Intent(this, RollerHome::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Brawo, próba logowania powiodła się.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Coś poszło nie tak, spróbuj zalogować się ponownie.",
                        Toast.LENGTH_SHORT).show()
                }
            }

            .addOnFailureListener {
                Toast.makeText(this, "Wystapił Błąd!! ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

    }
}