package com.example.desmovel

import android.icu.util.Calendar
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlin.apply
import kotlin.comparisons.maxOf
import kotlin.comparisons.minOf
import kotlin.let
import kotlin.text.uppercase

class EventosTelaDiariaFragment : Fragment() {

    private var _binding: FragmentEventosTelaDiariaBinding? = null
    private val binding get() = _binding!!

    // Ou sem ViewBinding:
    private lateinit var containerDias: LinearLayout
    private lateinit var recyclerEventosDia: RecyclerView
    private lateinit var tvTitulo: TextView

    private var diaSelecionado: Int = 1
    private var mesSelecionado: Int = 1
    private var anoSelecionado: Int = 2025

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Com ViewBinding:
        // _binding = FragmentEventosTelaDiariaBinding.inflate(inflater, container, false)
        // return binding.root

        // Sem ViewBinding:
        return inflater.inflate(R.layout.eventos_tela_diaria, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar views
        containerDias = view.findViewById(R.id.containerDias)
        recyclerEventosDia = view.findViewById(R.id.recyclerEventosDia)
        tvTitulo = view.findViewById(R.id.tvTitulo)

        // Receber dados dos arguments (vindos do fragment anterior)
        arguments?.let {
            diaSelecionado = it.getInt("DIA_SELECIONADO", 1)
            mesSelecionado = it.getInt("MES_SELECIONADO", 1)
            anoSelecionado = it.getInt("ANO_SELECIONADO", 2025)
        }

        // Configurar título do mês
        tvTitulo.text = obterNomeMes(mesSelecionado).uppercase()

        // ⭐ AQUI VAI O CÓDIGO DE ADICIONAR OS DIAS
        preencherDiasMes(diaSelecionado, mesSelecionado, anoSelecionado)

        // Carregar eventos do dia
        carregarEventosDoDia(diaSelecionado, mesSelecionado, anoSelecionado)

        // Botão voltar
        view.findViewById<ImageButton>(R.id.botao_voltar).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun preencherDiasMes(diaSelecionado: Int, mes: Int, ano: Int) {
        containerDias.removeAllViews() // Limpar antes de adicionar

        val calendar = Calendar.getInstance()
        calendar.set(ano, mes - 1, 1)
        val diasNoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Definir range de dias para mostrar (ex: 7 dias ao redor do dia selecionado)
        val diaInicio = maxOf(1, diaSelecionado - 3)
        val diaFim = minOf(diasNoMes, diaSelecionado + 3)

        for (dia in diaInicio..diaFim) {
            val textViewDia = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    40.dp,
                    40.dp
                ).apply {
                    marginEnd = 12.dp
                }
                text = dia.toString()
                gravity = Gravity.CENTER
                textSize = 14f

                // Estilo baseado se é o dia selecionado
                if (dia == diaSelecionado) {
                    setTextColor(Color.parseColor("#2196F3"))
                    setTypeface(null, Typeface.BOLD)
                    setBackgroundResource(R.drawable.background_dia_selecionado)
                } else {
                    setTextColor(Color.parseColor("#666666"))
                    setBackgroundResource(R.drawable.background_dia_nao_selecionado)
                }

                // Clique para trocar de dia
                setOnClickListener {
                    // Navegar para o mesmo fragment com novo dia
                    val bundle = Bundle().apply {
                        putInt("DIA_SELECIONADO", dia)
                        putInt("MES_SELECIONADO", mes)
                        putInt("ANO_SELECIONADO", ano)
                    }

                    val fragment = EventosTelaDiariaFragment().apply {
                        arguments = bundle
                    }

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
            containerDias.addView(textViewDia)
        }
    }

    private fun carregarEventosDoDia(dia: Int, mes: Int, ano: Int) {
        // Aqui você carrega os eventos do banco de dados/API
        // e configura o RecyclerView

        // Exemplo:
        // val eventos = viewModel.getEventosDoDia(dia, mes, ano)
        // val adapter = EventosAdapter(eventos)
        // recyclerEventosDia.adapter = adapter
        // recyclerEventosDia.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun obterNomeMes(mes: Int): String {
        val meses = arrayOf(
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        )
        return meses[mes - 1]
    }

    // Extension property para converter dp para pixels
    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Se estiver usando ViewBinding
    }

    companion object {
        fun newInstance(dia: Int, mes: Int, ano: Int) = EventosTelaDiariaFragment().apply {
            arguments = Bundle().apply {
                putInt("DIA_SELECIONADO", dia)
                putInt("MES_SELECIONADO", mes)
                putInt("ANO_SELECIONADO", ano)
            }
        }
    }
}