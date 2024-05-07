package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import id.ac.ukdw.skripsilansiaku.adapter.DataKlinikAdapter
import id.ac.ukdw.skripsilansiaku.dataclass.DataVIP
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl

class FasilitasLayananActivity : AppCompatActivity() {

    var oldDataConf: KonfirmasiMdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fasilitas_layanan)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@FasilitasLayananActivity, FasilitasKesehatanActivity::class.java)
            )
        }

        if(intent.extras != null){
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
        }

        val klinikList = listOf<DataVIP>(
            DataVIP(
                R.drawable.icon_vip,
                "Klinik Rawat Jalan VIP",
                "Sudah termasuk nomor antrian"
            ),
            DataVIP(
                R.drawable.icon_rawatjalan,
                "Klinik Rawat Jalan Reguler",
                "Sudah termasuk nomor antrian"
            )
//            ,
//            DataVIP(
//                R.drawable.icon_rawatinap,
//                "Klinik Rawat Inap",
//                "Tersedia berbagai macam tipe kamar"
//            )
        )
        val recyclerView = findViewById<RecyclerView>(R.id.RvKlinik)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = DataKlinikAdapter(this, klinikList){
            val confData = KonfirmasiMdl(
                oldDataConf?.rsData!!,
                it
            )
            val intent = Intent(this,PilihDokterActivity::class.java)
            intent.putExtra(Utils.CONFIRMATION_DATA, confData)
            startActivity(intent)
        }

    }
}