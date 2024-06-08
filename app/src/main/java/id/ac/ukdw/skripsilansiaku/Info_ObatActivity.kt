package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import id.ac.ukdw.skripsilansiaku.adapter.AdapterInfoObat

import id.ac.ukdw.skripsilansiaku.dataclass.DataObatClass
import java.util.Locale

class Info_ObatActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_info_obat)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this@Info_ObatActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        val AmbroxolBtn = findViewById<Button>(R.id.button_Ambroxol)
        AmbroxolBtn.setOnClickListener {
            startActivity(
                Intent(this@Info_ObatActivity, Info_Obat_Ambroxol_Activity::class.java)
            )
        }
        val IbuprofenBtn = findViewById<Button>(R.id.button_Ibuproven)
        IbuprofenBtn.setOnClickListener {
            startActivity(
                Intent(this@Info_ObatActivity, Info_Obat_IbuProven_Activity::class.java)
            )
        }
        val button_ObaDemam = findViewById<Button>(R.id.button_ObatDemam)
        button_ObaDemam.setOnClickListener {
            startActivity(
                Intent(this@Info_ObatActivity, Info_Obat_button_ObatDemam_Activity::class.java)
            )
        }

        imageListObat = arrayOf(
            R.drawable.omeprazole,
            R.drawable.cetirizine,
            R.drawable.vertigosan,
            R.drawable.arimed,
            R.drawable.xintrol
        )
        jenisListObat = arrayOf(
            "Obat Gangguan Pencernaan",
            "Obat Kulit",
            "Obat Vertigo",
            "Obat Antinyeri",
            "Obat Mata"

        )
        titleListObat = arrayOf(
            "Obat untuk mengatasi gangguan asam lambung tinggi, Obat apa?",
            "Alergi bisa datang dalam beragam bentuk.Cetirizine untuk obat apa?",
            "Vertigosan digunakan untuk vertigo,Hlangnya pendengaran yang?",
            "Arimed adalah sediaan obat yang digunakan untuk mengurangi rasa nyeri",
            "Cendo Xitrol digunakan untuk membantu mengatasi peradangan pada mata"
        )
        penjelasanListObat = arrayOf(
            "Obat yang digunakan untuk menangani keluhan-keluhan asam lambung berlebih..",
            "Hipersensitivitas atau alergi bisa datang dalam beragam bentuk. Misalnya, gatal..",
            "Vertigosan obat yang diproduksi oleh PT Sanbe Farma. Vertigosan mengandung..",
            "Obat yang mengandung Meloxicam, digunakan untuk mengurangi nyeri yang...",
            "Iritasi mata yang disertai infeksi biasanya membuat mata jadi merah, perih.."
        )
        descList = arrayOf(
            getString(R.string.Omeprazole),
            getString(R.string.cetirizine),
            getString(R.string.vertigo),
            getString(R.string.arimed),
            getString(R.string.Xintrol)
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
            val dataClass = DataObatClass(imageListObat[i],jenisListObat[i],titleListObat[i],penjelasanListObat[i],descList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterInfoObat(searchList)
    }
}