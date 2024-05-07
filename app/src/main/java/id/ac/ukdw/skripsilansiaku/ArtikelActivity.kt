package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.AdapterClassArtikel
import id.ac.ukdw.skripsilansiaku.dataclass.DataArtikelClass
import java.util.Locale

class ArtikelActivity : AppCompatActivity() {

    private lateinit var recyclerViewArtikel: RecyclerView
    private lateinit var dataList: ArrayList<DataArtikelClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var penjelasanList: Array<String>
    lateinit var kategoriList: Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClassArtikel
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataArtikelClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artikel)


        val btnKolestrol = findViewById<Button>(R.id.btn_Kolestrol_artikel)
        btnKolestrol.setOnClickListener {
            startActivity(
                Intent(this@ArtikelActivity, btnKolestrolActivity::class.java)
            )
        }

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this@ArtikelActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }


        imageList = arrayOf(
            R.drawable.artikel_diabetes,
            R.drawable.jantung_artikel,
            R.drawable.stroke_artikel,
            R.drawable.hipertensi_artikel,
            R.drawable.hidup_sehat,
            R.drawable.ekg_jantung,
            R.drawable.mata_artikel_awal,
            R.drawable.kulit_lupas
        )

        titleList = arrayOf(
            "Diabetes pada Lansia, Ini Gejala dan Cara Mengendalikannya",
            "Mengapa Penyakit Jantung Koroner Rentan Dialami Lansia?",
            "Stroke pada Lansia, Waspada Faktor Risikonya",
            "Atasi Hipertensi pada Lansia Dengan Cara Ini",
            "Tak Sekadar Nyaman, Inilah Tips Menciptakan Rumah Aman untuk Lansia",
            "Kapan Anda Memerlukan Pemeriksaan EKG Jantung?",
            "Makula, Mengenal Lebih Dalam Perannya dalam Proses Penglihatan",
            "Kulit Kaki Mengelupas, Ketahui Penyebab dan Cara Mengatasinya"
        )
        //kategori
        penjelasanList = arrayOf(
            "Diabetes",
            "Jantung",
            "Stroke",
            "Hipertensi",
            "Hidup Sehat",
            "Jantung",
            "Mata",
            "Kulit"
        )

        //detailpenjelasan
        kategoriList = arrayOf(
            getString(R.string.diabetes),
            getString(R.string.jantung),
            getString(R.string.stroke),
            getString(R.string.hipertensi),
            getString(R.string.hidupsehat),
            getString(R.string.ekg),
            getString(R.string.mata_awal),
            getString(R.string.kulit)
        )
//      //Penjelasan
        descList = arrayOf(
            "Pada dasarnya, diabetes adalah penyakit yang bisa dialami oleh siapa saja, namun cenderung lebih rentan.....",
            "Penyakit jantung koroner termasuk penyakit berbahaya karena selain bisa menyebabkan serangan jantung juga .....",
            "Ini adalah situasi darurat yang terjadi ketika aliran darah ke otak terhenti. Perlu diketahui, otak membutuhkan pasokan .....",
            "Hipertensi sendiri merupakan salah satu penyakit degeneratif yang terjadi akibat asupan natrium yang berlebih.",
            "Menciptakan rumah aman untuk lansia penting dilakukan, terutama bila Anda sedang merawat lansia. Hal ini karena..",
            "Elektrokardiogram (EKG) adalah pemeriksaan yang mengukur dan merekam aktivitas listrik jantung. Kapan perlu EKG?.",
            "Makula merupakan bagian kecil dalam mata yang berada di tengah retina. Bagian ini bertugas untuk...",
            "Kulit kaki mengelupas bisa mengganggu penampilan dan menimbulkan rasa tidak nyaman. Kondisi ini disebabkan oleh..."
        )
//


        recyclerViewArtikel = findViewById(R.id.rv_Artikel)
        searchView = findViewById(R.id.search)
        recyclerViewArtikel.layoutManager = LinearLayoutManager(this)
        recyclerViewArtikel.setHasFixedSize(true)

        dataList = arrayListOf<DataArtikelClass>()
        searchList = arrayListOf<DataArtikelClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    dataList.forEach {
                        if (it.dataTitleArtikel.lowercase(Locale.getDefault())
                                .contains(searchText)
                        ) {
                            searchList.add(it)
                        }
                    }
                    recyclerViewArtikel.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerViewArtikel.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })

        myAdapter = AdapterClassArtikel(searchList)
        recyclerViewArtikel.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivityArtikel::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataArtikelClass(
                imageList[i],
                titleList[i],
                descList[i],
                penjelasanList[i],
                kategoriList[i]
            )
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerViewArtikel.adapter = AdapterClassArtikel(searchList)
    }
}