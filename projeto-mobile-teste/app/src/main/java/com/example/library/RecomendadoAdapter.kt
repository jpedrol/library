package com.example.library

import com.bumptech.glide.Glide
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RecomendadoAdapter(
    private val capas: List<Int>
) : RecyclerView.Adapter<RecomendadoAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgCapa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recomendado, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(holder.img)
            .load(capas[position])
            .override(600, 400)
            .downsample(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.AT_MOST)
            .format(com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565)
            .centerCrop()
            .dontAnimate()
            .into(holder.img)

        holder.img.setOnClickListener {
            val ctx = holder.img.context
            ctx.startActivity(Intent(ctx, TelaLivroDetalheActivity::class.java))
        }
    }

    override fun getItemCount() = capas.size
}
