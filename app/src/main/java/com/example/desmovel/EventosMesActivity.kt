package com.example.desmovel

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class EventosMesActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var recyclerEventosMes: RecyclerView
    private lateinit var botaoVoltar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eventos_tela_mensal)

        // Inicializar views
        calendarView = findViewById(R.id.calendarView)
        recyclerEventosMes = findViewById(R.id.recyclerEventosMes)
        botaoVoltar = findViewById(R.id.botao_voltar)

        // Configurar botão voltar
        botaoVoltar.setOnClickListener {
            finish()
        }

        // Configurar RecyclerView
        setupRecyclerView()

        // Configurar listener do calendário
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            navegarParaTelaDiaria(dayOfMonth, month + 1, year)
        }

        // Carregar eventos do mês atual
        carregarEventosDoMes()
    }

    private fun setupRecyclerView() {
        recyclerEventosMes.layoutManager = LinearLayoutManager(this)
        // TODO: Configurar o adapter com os dados dos eventos
        // recyclerEventosMes.adapter = EventosAdapter(listaEventos)
    }

    private fun navegarParaTelaDiaria(dia: Int, mes: Int, ano: Int) {
        val intent = Intent(this, EventosTelaDiariaActivity::class.java)
        intent.putExtra("DIA_SELECIONADO", dia)
        intent.putExtra("MES_SELECIONADO", mes)
        intent.putExtra("ANO_SELECIONADO", ano)
        startActivity(intent)
    }

    private fun carregarEventosDoMes() {
        // TODO: Carregar eventos do banco de dados/API
        val calendar = Calendar.getInstance()
        val mesAtual = calendar.get(Calendar.MONTH) + 1
        val anoAtual = calendar.get(Calendar.YEAR)

        // Exemplo: viewModel.getEventosDoMes(mesAtual, anoAtual)
    }
}