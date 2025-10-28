package com.example.library

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_inicial)

        val vp = findViewById<ViewPager2>(R.id.vpRecomendados)
        val tabDots = findViewById<TabLayout>(R.id.tabDots)
        val btnRefresh = findViewById<ImageButton>(R.id.btnRefreshPill)

        val capas = listOf(R.drawable.livro1, R.drawable.livro2, R.drawable.livro3)
        vp.adapter = RecomendadoAdapter(capas)

        val sidePad = (16 * resources.displayMetrics.density).toInt()
        val pageMargin = (12 * resources.displayMetrics.density).toInt()

        vp.clipToPadding = false
        vp.clipChildren = false
        vp.offscreenPageLimit = 3
        vp.setPadding(sidePad, 0, sidePad, 0)
        vp.setPageTransformer(MarginPageTransformer(pageMargin))

        TabLayoutMediator(tabDots, vp) { tab, _ ->
            tab.icon = ContextCompat.getDrawable(this, R.drawable.dot_inactive)
        }.attach()

        for (i in 0 until tabDots.tabCount) {
            tabDots.getTabAt(i)?.icon = ContextCompat.getDrawable(
                this, if (i == 0) R.drawable.dot_active else R.drawable.dot_inactive
            )
        }

        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until tabDots.tabCount) {
                    tabDots.getTabAt(i)?.icon = ContextCompat.getDrawable(
                        this@MenuInicialActivity,
                        if (i == position) R.drawable.dot_active else R.drawable.dot_inactive
                    )
                }
            }
        })

        btnRefresh.setOnClickListener {
            Toast.makeText(this, "Atualizando recomendados…", Toast.LENGTH_SHORT).show()
        }
    }
}
