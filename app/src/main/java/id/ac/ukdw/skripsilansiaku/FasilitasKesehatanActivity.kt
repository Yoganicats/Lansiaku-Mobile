package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.RumahSakitAdapter
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl
import id.ac.ukdw.skripsilansiaku.dataclass.RumahSakitDataClass

import java.util.*
import kotlin.collections.ArrayList

class FasilitasKesehatanActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<RumahSakitDataClass>

    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var alamatList: Array<String>
    lateinit var jadwalList: Array<String>
    lateinit var ratingList: Array<String>


    private lateinit var myAdapter: RumahSakitAdapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<RumahSakitDataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fasilitas_kesehatan)

        //Explicit Intent
        //1. Move Activity
        val backtemu = findViewById<ImageButton>(R.id.backbtn)
        backtemu.setOnClickListener {
            val intent = Intent(this,TemudokterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK )
            startActivity(intent)

        }



        imageList = arrayOf(
            R.drawable.sardjito,
            R.drawable.pantirapih,
            R.drawable.bethesda,
            R.drawable.siloam,
            R.drawable.ludira,
            R.drawable.dktjogja
        )

        titleList = arrayOf(
            "RS Dr.Sardjito",
            "RS Panti Rapih Yogyakarta",
            "RS Bethesda Yogyakarta",
            "RS Siloam Yogyakarta",
            "RS Ludira Husada Tama",
            "RS dr Soetarto",
        )

        alamatList = arrayOf(
            "Jl. Kesehatan Sendowo No.1, Sendowo, Sinduadi, Kec. Mlati, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281",
            "Jl. Cik Di Tiro No.30, Samirono, Terban, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55223",
            "Jl. Jend. Sudirman No.70, Kotabaru, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55224",
            "Jl. Laksda Adisucipto No.32-34, Demangan, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55221",
            "Jl. Wiratama No.4, Tegalrejo, Kec. Tegalrejo, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55244",
            "Jl. Juadi No.19, Kotabaru, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55224",

            )
        jadwalList = arrayOf(
            "1.8 km",
            "3.2 km",
            "4.3 km",
            "1.2 km",
            "1.5 km",
            "5.5 km",

            )

        ratingList = arrayOf(
            "4.8 (210 Rates)",
            "4.7 (310 Rates)",
            "4.9 (240 Rates)",
            "4.6 (180 Rates)",
            "4.9 (320 Rates)",
            "4.9 (320 Rates)",

            )

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<RumahSakitDataClass>()
        searchList = arrayListOf<RumahSakitDataClass>()
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
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    dataList.forEach {
                        if (it.dataTitle.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        //intent
        myAdapter = RumahSakitAdapter(searchList)
        recyclerView.adapter = myAdapter
        myAdapter.onItemClick = {
            val confData = KonfirmasiMdl(
                it
            )
            val intent = Intent(this@FasilitasKesehatanActivity, FasilitasLayananActivity::class.java)
            intent.putExtra(Utils.CONFIRMATION_DATA, confData)
            startActivity(intent)
        }

    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = RumahSakitDataClass(imageList[i], titleList[i], alamatList[i], jadwalList[i], ratingList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = RumahSakitAdapter(searchList)
    }


}