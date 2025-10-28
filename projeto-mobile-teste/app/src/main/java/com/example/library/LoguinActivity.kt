package com.example.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoguinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loguin)

        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val linkRegistrar = findViewById<TextView>(R.id.linkRegistrar)

        btnEntrar.setOnClickListener {
            try {
                startActivity(Intent(this@LoguinActivity, MenuInicialActivity::class.java))
            } catch (e: Exception) {
                Log.e("LoguinActivity", "Erro ao abrir MenuInicialActivity", e)
                Toast.makeText(this, "Erro ao abrir a tela: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        linkRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
