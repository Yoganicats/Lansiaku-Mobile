package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.DetailDokterKonsultasi.Companion.INTENT_PARAM
import id.ac.ukdw.skripsilansiaku.adapter.AdapterClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.adapter.AdapterTemuDokterSpesialis
import id.ac.ukdw.skripsilansiaku.adapter.PilihDokterAdapter
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.NamaDokterData
import java.util.Locale

class Pilih_Dokter_KonsultasiActivity : AppCompatActivity() {

    private lateinit var recyclerViewKonsultasi: RecyclerView
    private lateinit var DokterKonsultasiList: ArrayList<DataClassDokterKonsultasi>

    private lateinit var myAdapter: AdapterClassDokterKonsultasi

    private lateinit var searchView: SearchView

    private lateinit var searchList: ArrayList<DataClassDokterKonsultasi>


    lateinit var imageListDokterkonsultasi: Array<Int>
    lateinit var namaDokterKonsultasi: Array<String>
    lateinit var SpesialisDokterKonsultasi: Array<String>
    lateinit var hargaDokterKonsultasi: Array<String>
    lateinit var listtxtMulaiKonsul: Array<String>
    lateinit var listdokterBekerja: Array<String>
    lateinit var durasiKonsultasi: Array<String>
    lateinit var jumlahPasienKonsultasi: Array<String>

    lateinit var keahlianDokter: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_dokter_konsultasi)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this, KonsultasiActivity::class.java)

            startActivity(intent)
        }
        imageListDokterkonsultasi = arrayOf(
            R.drawable.diah_permata,
            R.drawable.kornelis,
            R.drawable.putu_ayu,
            R.drawable.khairuddin
        )
        namaDokterKonsultasi = arrayOf(
            "dr. Diah Permata Kinanti, Sp. P",
            "dr. Kornelis Aribowo, Sp. P",
            "dr. Ni Putu Ayu Widiasari, Sp. P",
            "dr. Khairuddin Hamdani, Sp. P"

        )
        SpesialisDokterKonsultasi = arrayOf(
            "Spesialis Paru",
            "Spesialis Paru",
            "Spesialis Paru",
            "Spesialis Paru"
        )
        hargaDokterKonsultasi = arrayOf(
            "Rp 100.000",
            "Rp 80.000",
            "Rp 60.000",
            "Rp 40.000"
        )


        durasiKonsultasi = arrayOf(
            "20 Menit",
            "30 Menit",
            "50 Menit",
            "30 Menit"
        )
        jumlahPasienKonsultasi = arrayOf(
            "11 Pasien",
            "6 Pasien",
            "9 Pasien",
            "20 Pasien"
        )
        listdokterBekerja = arrayOf(
            "9 tahun",
            "10 tahun",
            "1 tahun",
            "11 tahun"
        )

        listtxtMulaiKonsul = arrayOf(
            "Mulai Konsultasi",
            "Mulai Konsultasi",
            "Mulai Konsultasi",
            "Mulai Konsultasi"
        )


        keahlianDokter = arrayOf(
            getString(R.string.DiahPermata),
            getString(R.string.Kornelis),
            getString(R.string.Putu_Ayu),
            getString(R.string.Khairuddin)
        )

        recyclerViewKonsultasi = findViewById(R.id.rvPilihDokterKonsultasi)
        searchView = findViewById(R.id.search)
        recyclerViewKonsultasi.layoutManager = LinearLayoutManager(this)
        recyclerViewKonsultasi.setHasFixedSize(true)

        DokterKonsultasiList = arrayListOf<DataClassDokterKonsultasi>()
        searchList = arrayListOf<DataClassDokterKonsultasi>()
        getData()

        //serching
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    DokterKonsultasiList.forEach {
                        if (it.namaDokterKonsultasi.toLowerCase(Locale.getDefault())
                                .contains(searchText)
                        ) {
                            searchList.add(it)
                        }
                    }
                    recyclerViewKonsultasi.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(DokterKonsultasiList)
                    recyclerViewKonsultasi.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })


        myAdapter = AdapterClassDokterKonsultasi(searchList)
        recyclerViewKonsultasi.adapter = myAdapter
        myAdapter.onItemClick = {
            val intent = Intent(this, DetailDokterKonsultasi::class.java)
            intent.putExtra(INTENT_PARAM, it)
            startActivity(intent)
        }

    }

    private fun getData() {
        for (i in imageListDokterkonsultasi.indices) {

            val dataClass = DataClassDokterKonsultasi(
                imageListDokterkonsultasi[i],
                namaDokterKonsultasi[i],
                SpesialisDokterKonsultasi[i],
                hargaDokterKonsultasi[i],
                listdokterBekerja[i],
                listtxtMulaiKonsul[i],
                durasiKonsultasi[i],
                jumlahPasienKonsultasi[i],

            )
            DokterKonsultasiList.add(dataClass)
        }
        searchList.addAll(DokterKonsultasiList)
        recyclerViewKonsultasi.adapter = AdapterClassDokterKonsultasi(searchList)
    }
}
