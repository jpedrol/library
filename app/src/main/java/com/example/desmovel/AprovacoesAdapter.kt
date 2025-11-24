package com.example.desmovel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AprovacoesAdapter(
    private val solicitacoes: List<SolicitacaoReserva>,
    private val onAprovarClick: (SolicitacaoReserva) -> Unit
) : RecyclerView.Adapter<AprovacoesAdapter.AprovacaoViewHolder>() {

    inner class AprovacaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTituloSolicitacao: TextView = itemView.findViewById(R.id.tvTituloSolicitacao)
        val tvDescricaoSolicitacao: TextView = itemView.findViewById(R.id.tvDescricaoSolicitacao)
        val tvQuantidadeExemplares: TextView = itemView.findViewById(R.id.tvQuantidadeExemplares)
        val btnAprovar: Button = itemView.findViewById(R.id.btnAprovar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AprovacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aprovacao_reserva, parent, false)
        return AprovacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AprovacaoViewHolder, position: Int) {
        val solicitacao = solicitacoes[position]

        holder.tvTituloSolicitacao.text = "Solicitação de reserva de livro:"
        holder.tvDescricaoSolicitacao.text = solicitacao.getDescricao()
        holder.tvQuantidadeExemplares.text =
            "Quantidade de exemplares: ${solicitacao.quantidadeExemplares}"

        // Desabilita o botão se não houver exemplares disponíveis
        holder.btnAprovar.isEnabled = solicitacao.quantidadeExemplares > 0
        holder.btnAprovar.alpha = if (solicitacao.quantidadeExemplares > 0) 1.0f else 0.5f

        holder.btnAprovar.setOnClickListener {
            onAprovarClick(solicitacao)
        }
    }

    override fun getItemCount(): Int = solicitacoes.size
}