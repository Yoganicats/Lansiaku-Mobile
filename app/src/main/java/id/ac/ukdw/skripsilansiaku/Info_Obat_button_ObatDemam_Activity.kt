package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.AdapterInfoObat
import id.ac.ukdw.skripsilansiaku.dataclass.DataObatClass
import java.util.Locale

class Info_Obat_button_ObatDemam_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataObatClass>


    lateinit var imageListObat: Array<Int>
    lateinit var jenisListObat: Array<String>
    lateinit var titleListObat: Array<String>
    lateinit var penjelasanListObat: Array<String>

    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>

    private lateinit var myAdapterInfoObat: AdapterInfoObat
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataObatClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_obat_button_obat_demam)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@Info_Obat_button_ObatDemam_Activity, Info_ObatActivity::class.java)
            )
        }


        imageListObat = arrayOf(
            R.drawable.paracetamol,
            R.drawable.axaprofen,
            R.drawable.panasamol
        )
        jenisListObat = arrayOf(
            "Obat Demam",
            "Obat Demam",
            "Obat Demam"

        )
        titleListObat = arrayOf(
            "Kenali Paracetamol, Manfaat, Dosis, serta Efek Samping",
            "Axaprofen adalah obat yang mengandung obat apa?",
            "Kenali Obat Panamaol, Manfaat dan dosis, serta efeknya?"
        )
        penjelasanListObat = arrayOf(
            "Paracetamol merupakan obat yang dapat digunakan untuk meringankan rasa sakit pada..",
            "Axaprofen digunakan untuk meredakan demam dan nyeri...",
            "Panasamol digunakan untuk meredakan nyeri ringan hingga sedang, dan.."
        )
        descList = arrayOf(
            getString(R.string.Paracetamol),
            getString(R.string.Axaprofen),
            getString(R.string.vertigo)
        )


        recyclerView = findViewById(R.id.rV_Obat)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataObatClass>()
        searchList = arrayListOf<DataObatClass>()
        getData()

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
                    dataList.forEach {
                        if (it.dataTitleObat.toLowerCase(Locale.getDefault())
                                .contains(searchText)
                        ) {
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
        myAdapterInfoObat = AdapterInfoObat(searchList)
        recyclerView.adapter = myAdapterInfoObat
        myAdapterInfoObat.onItemClick = {
            val intent = Intent(this, Detail_Info_obat_Activity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

    }

    private fun getData() {
        for (i in imageListObat.indices) {
            val dataClass = DataObatClass(
                imageListObat[i],
                jenisListObat[i],
                titleListObat[i],
                penjelasanListObat[i],
                descList[i]
            )
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterInfoObat(searchList)
    }
}