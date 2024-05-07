package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassInfoPenyakit
import id.ac.ukdw.skripsilansiaku.dataclass.DataObatClass

class Detail_Info_obat_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info_obat)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@Detail_Info_obat_Activity, Info_ObatActivity::class.java)
            )
        }
        val getData = intent.getParcelableExtra<DataObatClass>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.detailTitle)
            val detailDesc: TextView = findViewById(R.id.detailDesc)
            val detailImage: ImageView = findViewById(R.id.detailImage)
            val detailKategori: TextView = findViewById(R.id.tv_kategori_artikel)
            detailTitle.text = getData.dataTitleObat
            detailDesc.text = getData.dataDesc
            detailKategori.text =getData.dataJenisObat
            detailImage.setImageResource(getData.dataImageObat)
        }
    }
}