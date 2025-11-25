package com.example.desmovel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configurar padding para edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar botões
        setupButtons()
    }

    private fun setupButtons() {
        // Botão Ver Eventos
        findViewById<Button>(R.id.btnEventos)?.setOnClickListener {
            abrirEventos()
        }

        // Botão Pesquisar Livros (se você tiver essa funcionalidade)
        findViewById<Button>(R.id.btnPesquisarLivros)?.setOnClickListener {
            // TODO: Abrir tela de pesquisa de livros
        }

        // Botão Minhas Reservas (se você tiver essa funcionalidade)
        findViewById<Button>(R.id.btnReservas)?.setOnClickListener {
            // TODO: Abrir tela de reservas
        }

        // Botão Criar Evento (Admin)
        findViewById<Button>(R.id.btnCriarEvento)?.setOnClickListener {
            abrirCriarEvento()
        }
    }

    private fun abrirEventos() {
        val intent = Intent(this, EventosMesActivity::class.java)
        startActivity(intent)
    }

    private fun abrirCriarEvento() {
        val intent = Intent(this, CriarEventoActivity::class.java)
        startActivity(intent)
    }
}