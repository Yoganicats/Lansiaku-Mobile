package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.PilihDokterAdapter
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl
import id.ac.ukdw.skripsilansiaku.dataclass.NamaDokterData


class PilihDokterActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dokterList: ArrayList<NamaDokterData>
    private lateinit var pilihDokterAdapter: PilihDokterAdapter

    var oldDataConf: KonfirmasiMdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_dokter)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this@PilihDokterActivity,FasilitasLayananActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY )
            startActivity(intent)

        }

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
        }

        recyclerView = findViewById(R.id.rvKlinik)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dokterList = ArrayList()

        dokterList.add(
            NamaDokterData(
                R.drawable.alvin_tonang,
                "dr. Alvin Tonang, Sp. JP, FIHA",
                "Spesialis Jantung",
                "Rp 130.000"
            )
        )
        dokterList.add(
            NamaDokterData(
                R.drawable.galih,
                "dr. Galih Rakasiwi Soekarno, Sp. JP",
                "Spesialis Jantung",
                "Rp 230.000"
            )
        )
        dokterList.add(
            NamaDokterData(
                R.drawable.bobby_arfhan,
                "dr. Bobby Arfhan A, Sp. JP (K),FIHA",
                "Spesialis Jantung",
                "Rp 420.000"
            )
        )
        dokterList.add(
            NamaDokterData(
                R.drawable.zakky,
                "dr. Mohammad Zakky Fananie, Sp. JP, FIHA",
                "Spesialis Jantung",
                "Rp 450.000"
            )
        )

        pilihDokterAdapter = PilihDokterAdapter(dokterList)
        recyclerView.adapter = pilihDokterAdapter

        pilihDokterAdapter.onItemClick = {
            /*val confData = KonfirmasiMdl(
                oldDataConf?.rsData,
                oldDataConf?.jnsData,
                it
            )
            val intent = Intent(this, KonfirmasiDataActivity::class.java)

            intent.putExtra(Utils.CONFIRMATION_DATA, confData)

            startActivity(intent)*/

            val confData = KonfirmasiMdl(
                oldDataConf?.rsData!!,
                oldDataConf?.jnsData!!,
                it
            )
            val intent = Intent(this, Pilih_Jadwal_Temu_Dokter_2_Activity::class.java)

            intent.putExtra(Utils.CONFIRMATION_DATA, confData)

            startActivity(intent)
        }
    }
}