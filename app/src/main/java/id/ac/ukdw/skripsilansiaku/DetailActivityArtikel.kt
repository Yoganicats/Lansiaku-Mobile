package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import id.ac.ukdw.skripsilansiaku.dataclass.DataArtikelClass

@Suppress("DEPRECATION")
class DetailActivityArtikel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artikel)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@DetailActivityArtikel, ArtikelActivity::class.java)
            )
        }

        val getData = intent.getParcelableExtra<DataArtikelClass>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val detailImage: ImageView = findViewById(R.id.detailImage)
            val detailKategori:TextView= findViewById(R.id.tv_kategori_artikel)
            detailTitle.text = getData.dataTitleArtikel
            detailDesc.text = getData.dataDesc
            detailKategori.text =getData.dataKategori
            detailImage.setImageResource(getData.dataImageArtikel)
        }
    }
}