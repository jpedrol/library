package com.example.desmovel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DetalhesEventoActivity : AppCompatActivity() {

    private lateinit var botaoVoltar: ImageButton
    private lateinit var tvTituloEvento: TextView
    private lateinit var tvNomeEventoCard: TextView
    private lateinit var tvTipoEvento: TextView
    private lateinit var tvDataHora: TextView
    private lateinit var tvLocalEvento: TextView
    private lateinit var ivImagemEvento: ImageView
    private lateinit var tvDescricaoConvidado: TextView
    private lateinit var btnEditar: Button
    private lateinit var btnExcluir: Button

    private var eventoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes_evento)

        // Inicializar views
        botaoVoltar = findViewById(R.id.botao_voltar)
        tvTituloEvento = findViewById(R.id.tvTituloEvento)
        tvNomeEventoCard = findViewById(R.id.tvNomeEventoCard)
        tvTipoEvento = findViewById(R.id.tvTipoEvento)
        tvDataHora = findViewById(R.id.tvDataHora)
        tvLocalEvento = findViewById(R.id.tvLocalEvento)
        ivImagemEvento = findViewById(R.id.ivImagemEvento)
        tvDescricaoConvidado = findViewById(R.id.tvDescricaoConvidado)
        btnEditar = findViewById(R.id.btnEditar)
        btnExcluir = findViewById(R.id.btnExcluir)

        // Receber ID do evento
        eventoId = intent.getStringExtra("eventoId")

        // Carregar dados do evento
        carregarDadosEvento()

        // Configurar listeners
        botaoVoltar.setOnClickListener {
            finish()
        }

        btnEditar.setOnClickListener {
            editarEvento()
        }

        btnExcluir.setOnClickListener {
            confirmarExclusao()
        }
    }

    private fun carregarDadosEvento() {
        // TODO: Carregar dados do evento do banco de dados usando eventoId
        // Exemplo de dados hardcoded para teste:
        tvTituloEvento.text = "8º CLUBE DE LEITURA BALAIO - A GUARDIÃ DA MINHA IRMÃ"
        tvNomeEventoCard.text = "8º Clube de Leitura Balaio - A guardiã da minha irmã"
        tvTipoEvento.text = "Clube do Livro"
        tvDataHora.text = "26/09/2025 11:00"
        tvLocalEvento.text = "Piso superior"
        tvDescricaoConvidado.text = "Graduação em Psicologia pela Unifor..."
    }

    private fun editarEvento() {
        // Navegar para tela de edição
        val intent = Intent(this, CriarEventoActivity::class)
        intent.putExtra("eventoId", eventoId)
        intent.putExtra("modo", "EDITAR")
        startActivity(intent)
    }

    private fun confirmarExclusao() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Excluir Evento")
        builder.setMessage("Tem certeza que deseja excluir este evento?")
        builder.setPositiveButton("Excluir") { dialog, which ->
            excluirEvento()
        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun excluirEvento() {
        // TODO: Excluir evento do banco de dados
        // Exemplo:
        // eventoRepository.delete(eventoId)

        Toast.makeText(this, "Evento excluído com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }
}