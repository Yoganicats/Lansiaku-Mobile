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
import id.ac.ukdw.skripsilansiaku.adapter.AdapterInfoPenyakit
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassInfoPenyakit
import java.util.Locale

class MasalahTidur_InfoPenyakit_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataPenyakitList: ArrayList<DataClassInfoPenyakit>

    lateinit var imageListPenyakit:Array<Int>
    lateinit var jenisListPenyakit:Array<String>
    lateinit var titleListPenyakit:Array<String>
    lateinit var penjelasanListPenyakit:Array<String>


    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterInfoPenyakit
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClassInfoPenyakit>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masalah_tidur_info_penyakit)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@MasalahTidur_InfoPenyakit_Activity, Info_PenyakitActivity::class.java)
            )
        }


        imageListPenyakit = arrayOf(
            R.drawable.familia_tidur,
            R.drawable.mendengkur,
            R.drawable.ganguan_nikotin
        )
        jenisListPenyakit = arrayOf(
            "Masalah Tidur",
            "Masalah Tidur",
            "Masalah Tidur"
        )
        titleListPenyakit = arrayOf(
            "Fatal familial insomnia",
            "Mendengkur adalah jenis gangguan tidur yang?",
            "Gangguan nikotin disebabkan oleh penggunaan tembakau"
        )
        penjelasanListPenyakit = arrayOf(
            "Fatal familial insomnia merupakan penyakit prion, atau jenis protein yang dapat...",
            "suara mendengkur yang cukup keras sering mengusik lelapnya tidur orang di sekitarnya..",
            "Hal ini meliputi keinginan yang kuat rokok, sehingga sulit mengontrol diri agar tidak merokok. Bahkan..."
        )
        descList = arrayOf(
            getString(R.string.familia),
            getString(R.string.Mendengkur),
            getString(R.string.gangguanrokok))



        recyclerView = findViewById(R.id.rV_penyakit)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataPenyakitList = arrayListOf<DataClassInfoPenyakit>()
        searchList = arrayListOf<DataClassInfoPenyakit>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataPenyakitList.forEach{
                        if (it.dataTitlePenyakit.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataPenyakitList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        myAdapter = AdapterInfoPenyakit(searchList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, Detail_InfoPenyakitActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }


    }
    private fun getData(){
        for (i in imageListPenyakit.indices){
            val dataClass = DataClassInfoPenyakit(imageListPenyakit[i], jenisListPenyakit[i],titleListPenyakit[i],penjelasanListPenyakit[i], descList[i])
            dataPenyakitList.add(dataClass)
        }
        searchList.addAll(dataPenyakitList)
        recyclerView.adapter = AdapterInfoPenyakit(searchList)
    }
}