package com.example.desmovel

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.mutableListOf

class AprovacoesUsuariosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AprovacoesAdapter
    private lateinit var btnVoltar: ImageView
    private val listaSolicitacoes = mutableListOf<SolicitacaoReserva>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprovacoes_usuarios)

        inicializarComponentes()
        configurarRecyclerView()
        carregarDados()
    }

    private fun inicializarComponentes() {
        recyclerView = findViewById(R.id.recyclerViewAprovacoes)
        btnVoltar = findViewById(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun configurarRecyclerView() {
        adapter = AprovacoesAdapter(
            listaSolicitacoes,
            onAprovarClick = { solicitacao ->
                aprovarReserva(solicitacao)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun carregarDados() {
        // Dados de exemplo
        listaSolicitacoes.apply {
            add(
                SolicitacaoReserva(
                    id = 1,
                    nomeUsuario = "Gabriel Gomes",
                    tituloLivro = "A Culpa é das Estrelas",
                    periodoInicio = "26",
                    periodoFim = "29",
                    mes = "setembro",
                    quantidadeExemplares = 10
                )
            )
            add(
                SolicitacaoReserva(
                    id = 2,
                    nomeUsuario = "Mariana Silva",
                    tituloLivro = "Dom Casmurro",
                    periodoInicio = "3",
                    periodoFim = "7",
                    mes = "outubro",
                    quantidadeExemplares = 5
                )
            )
            add(
                SolicitacaoReserva(
                    id = 3,
                    nomeUsuario = "Ricardo Almeida",
                    tituloLivro = "O Pequeno Príncipe",
                    periodoInicio = "10",
                    periodoFim = "14",
                    mes = "novembro",
                    quantidadeExemplares = 3
                )
            )
            add(
                SolicitacaoReserva(
                    id = 4,
                    nomeUsuario = "Beatriz Oliveira",
                    tituloLivro = "1984",
                    periodoInicio = "1º",
                    periodoFim = "5",
                    mes = "dezembro",
                    quantidadeExemplares = 0
                )
            )
        }

        adapter.notifyDataSetChanged()
    }

    private fun aprovarReserva(solicitacao: SolicitacaoReserva) {
        if (solicitacao.quantidadeExemplares > 0) {
            // Aqui você faria a chamada para a API para aprovar a reserva
            Toast.makeText(
                this,
                "Reserva aprovada com sucesso!",
                Toast.LENGTH_SHORT
            ).show()

            // Remove a solicitação da lista após aprovação
            val position = listaSolicitacoes.indexOf(solicitacao)
            if (position != -1) {
                listaSolicitacoes.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        } else {
            Toast.makeText(
                this,
                "Não há exemplares disponíveis para este livro.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

// Data class para representar uma solicitação de reserva
data class SolicitacaoReserva(
    val id: Int,
    val nomeUsuario: String,
    val tituloLivro: String,
    val periodoInicio: String,
    val periodoFim: String,
    val mes: String,
    val quantidadeExemplares: Int
) {
    fun getDescricao(): String {
        return "$nomeUsuario está solicitando a reserva do livro \"$tituloLivro\" para o período de $periodoInicio a $periodoFim de $mes."
    }
}