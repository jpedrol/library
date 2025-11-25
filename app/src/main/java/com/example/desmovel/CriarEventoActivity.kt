package com.example.desmovel

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CriarEventoActivity : AppCompatActivity() {

    private lateinit var tvTitulo: TextView
    private lateinit var etNome: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var etLocal: EditText
    private lateinit var etDataHora: EditText
    private lateinit var checkboxConvidados: CheckBox
    private lateinit var btnCriarEvento: Button

    private var modo: String? = null // "CRIAR" ou "EDITAR"
    private var eventoId: String? = null // ID do evento (para edição)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_evento)

        // Inicializar views
        tvTitulo = findViewById(R.id.tvTitulo)
        etNome = findViewById(R.id.etNome)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        etLocal = findViewById(R.id.etLocal)
        etDataHora = findViewById(R.id.etDataHora)
        checkboxConvidados = findViewById(R.id.checkboxConvidados)
        btnCriarEvento = findViewById(R.id.btnCriarEvento)

        // Recuperar informações da intent
        modo = intent.getStringExtra("modo") ?: "CRIAR"
        eventoId = intent.getStringExtra("eventoId")

        // Configurar layout conforme o modo
        if (modo == "EDITAR") {
            tvTitulo.text = "EDITAR EVENTO"
            btnCriarEvento.text = "Salvar Alterações"
            carregarDadosEventoParaEdicao()
        } else {
            tvTitulo.text = "CRIAR EVENTO"
            btnCriarEvento.text = "Criar Evento"
        }

        // Clique do botão principal
        btnCriarEvento.setOnClickListener {
            if (modo == "EDITAR") {
                atualizarEvento()
            } else {
                criarEvento()
            }
        }
    }

    private fun carregarDadosEventoParaEdicao() {
        // TODO: Buscar os dados do evento no banco usando o eventoId
        // Exemplo mockado:
        etNome.setText("8º Clube de Leitura Balaio")
        etLocal.setText("Piso superior")
        etDataHora.setText("26/09/2025 11:00")
        // spinnerTipo.setSelection(...), etc
    }

    private fun criarEvento() {
        // TODO: Salvar novo evento no banco
        Toast.makeText(this, "Evento criado com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun atualizarEvento() {
        // TODO: Atualizar evento existente no banco
        Toast.makeText(this, "Evento atualizado com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
