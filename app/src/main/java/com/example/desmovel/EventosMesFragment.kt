package com.example.desmovel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class EventosMesFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var recyclerEventosMes: RecyclerView
    private lateinit var botaoVoltar: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.eventos_tela_mensal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar views
        calendarView = view.findViewById(R.id.calendarView)
        recyclerEventosMes = view.findViewById(R.id.recyclerEventosMes)
        botaoVoltar = view.findViewById(R.id.botao_voltar)

        // Configurar botão voltar
        botaoVoltar.setOnClickListener {
            requireActivity().onBackPressed()
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
        recyclerEventosMes.layoutManager = LinearLayoutManager(requireContext())
        // TODO: Configurar o adapter com os dados dos eventos
        // recyclerEventosMes.adapter = EventosAdapter(listaEventos)
    }

    private fun navegarParaTelaDiaria(dia: Int, mes: Int, ano: Int) {
        val bundle = Bundle().apply {
            putInt("diaSelecionado", dia)
            putInt("mesSelecionado", mes)
            putInt("anoSelecionado", ano)
        }

        findNavController().navigate(
            R.id.action_eventosMesFragment_to_eventosTelaDiariaFragment,
            bundle
        )
    }

    private fun carregarEventosDoMes() {
        // TODO: Carregar eventos do banco de dados/API
        // Aqui você buscaria os eventos do mês atual
        val calendar = Calendar.getInstance()
        val mesAtual = calendar.get(Calendar.MONTH) + 1
        val anoAtual = calendar.get(Calendar.YEAR)

        // Exemplo: viewModel.getEventosDoMes(mesAtual, anoAtual)
    }
}