package com.example.library

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            // voltar para LoginActivity: fechar a activity atual
            println("Botão de voltar clicado!")
            finish()

            // Se quiser abrir explicitamente:
            // startActivity(Intent(this, LoginActivity::class.java))
            // finish()
        }
    }
}
