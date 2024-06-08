package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import id.ac.ukdw.skripsilansiaku.dataclass.DataArtikelClass
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassInfoPenyakit

class Detail_InfoPenyakitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info_penyakit)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@Detail_InfoPenyakitActivity, Info_PenyakitActivity::class.java)
            )
        }

        val getData = intent.getParcelableExtra<DataClassInfoPenyakit>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val detailImage: ImageView = findViewById(R.id.detailImage)
            val detailKategori: TextView = findViewById(R.id.tv_kategori_artikel)
            detailTitle.text = getData.dataTitlePenyakit
            detailDesc.text = getData.dataDesc
            detailKategori.text =getData.datajenisPenyakit
            detailImage.setImageResource(getData.dataImagePenyakit)
        }
    }
}