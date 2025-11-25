package com.example.desmovel

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toUpperCase
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventosTelaDiariaActivity : AppCompatActivity() {

    private lateinit var containerDias: LinearLayout
    private lateinit var recyclerEventosDia: RecyclerView
    private lateinit var tvTitulo: TextView
    private lateinit var botaoVoltar: ImageButton

    private var diaSelecionado: Int = 1
    private var mesSelecionado: Int = 1
    private var anoSelecionado: Int = 2025

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eventos_tela_diaria)

        // Inicializar views
        containerDias = findViewById(R.id.containerDias)
        recyclerEventosDia = findViewById(R.id.recyclerEventosDia)
        tvTitulo = findViewById(R.id.tvTitulo)
        botaoVoltar = findViewById(R.id.botao_voltar)

        // Receber dados da intent
        diaSelecionado = intent.getIntExtra("DIA_SELECIONADO", 1)
        mesSelecionado = intent.getIntExtra("MES_SELECIONADO", 1)
        anoSelecionado = intent.getIntExtra("ANO_SELECIONADO", 2025)

        // Configurar título do mês
        val nomeMes = obterNomeMes(mesSelecionado)
        tvTitulo.text = nomeMes.toUpperCase()

        // Adicionar os dias
        preencherDiasMes(diaSelecionado, mesSelecionado, anoSelecionado)

        // Configurar RecyclerView
        recyclerEventosDia.layoutManager = LinearLayoutManager(this)
        // TODO: Configurar adapter com eventos
        // recyclerEventosDia.adapter = EventosAdapter(listaEventos)

        // Botão voltar
        botaoVoltar.setOnClickListener {
            finish()
        }
    }

    private fun preencherDiasMes(diaSelecionado: Int, mes: Int, ano: Int) {
        containerDias.removeAllViews()

        // Mostrar 7 dias ao redor do dia selecionado
        val diaInicio = if (diaSelecionado - 3 > 0) diaSelecionado - 3 else 1
        val diaFim = if (diaSelecionado + 3 < 31) diaSelecionado + 3 else 31

        for (dia in diaInicio..diaFim) {
            val textViewDia = TextView(this)

            // Configurar tamanho
            val params = LinearLayout.LayoutParams(
                dpParaPixel(40),
                dpParaPixel(40)
            )
            params.rightMargin = dpParaPixel(12)
            textViewDia.layoutParams = params

            // Configurar texto
            textViewDia.text = dia.toString()
            textViewDia.gravity = Gravity.CENTER
            textViewDia.textSize = 14f

            // Configurar cor e background
            if (dia == diaSelecionado) {
                textViewDia.setTextColor(0xFF2196F3.toInt())
                textViewDia.setBackgroundResource(R.drawable.background_dia_selecionado)
            } else {
                textViewDia.setTextColor(0xFF666666.toInt())
                textViewDia.setBackgroundResource(R.drawable.background_dia_nao_selecionado)
            }

            // Clique para trocar de dia
            textViewDia.setOnClickListener {
                    val newIntent = Intent(this, EventosTelaDiariaActivity::class.java)
                newIntent.putExtra("DIA_SELECIONADO", dia)
                newIntent.putExtra("MES_SELECIONADO", mes)
                newIntent.putExtra("ANO_SELECIONADO", ano)
                startActivity(newIntent)
                finish()
            }

            containerDias.addView(textViewDia)
        }
    }

    private fun obterNomeMes(mes: Int): String {
        return when (mes) {
            1 -> "Janeiro"
            2 -> "Fevereiro"
            3 -> "Março"
            4 -> "Abril"
            5 -> "Maio"
            6 -> "Junho"
            7 -> "Julho"
            8 -> "Agosto"
            9 -> "Setembro"
            10 -> "Outubro"
            11 -> "Novembro"
            12 -> "Dezembro"
            else -> "Mês"
        }
    }

    private fun dpParaPixel(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }
}