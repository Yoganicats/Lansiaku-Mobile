package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.AdapterInfoPenyakit
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassInfoPenyakit
import java.util.Locale

class Info_PenyakitActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_info_penyakit)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this@Info_PenyakitActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        val hipertensiBtn = findViewById<Button>(R.id.button_Hipertensi)
        hipertensiBtn.setOnClickListener {
            startActivity(
                Intent(this@Info_PenyakitActivity,Hipertensi_Penyakit_Activity::class.java)
            )
        }
        val StrokeBtn = findViewById<Button>(R.id.button_Stroke)
        StrokeBtn.setOnClickListener {
            startActivity(
                Intent(this@Info_PenyakitActivity,Stroke_penyakit_Activity::class.java)
            )
        }
        val masalahTidurBtn = findViewById<Button>(R.id.button_MasalahTidur)
        masalahTidurBtn.setOnClickListener {
            startActivity(
                Intent(this@Info_PenyakitActivity,MasalahTidur_InfoPenyakit_Activity::class.java)
            )
        }

        imageListPenyakit = arrayOf(
            R.drawable.daibetes_penyakit,
            R.drawable.alergi_kacang,
            R.drawable.rematik
        )
        jenisListPenyakit = arrayOf(
            "Masalah Diabetes",
            "Masalah Alergi",
            "Rematik"
        )
        titleListPenyakit = arrayOf(
            "Diabetes pada Lansia, Ini Gejala dan Cara Mengendalikannya",
            "Alergi Kacang Merupakan salah satu Alergi yang Sering dijumpai",
            "Demam Reumatik, atau Rheumatic Fever pada Lansia"
        )
        penjelasanListPenyakit = arrayOf(
            "Pada dasarnya, diabetes adalah penyakit yang bisa dialami oleh siapa saja...",
            "Alergi kacang jenis alergi yang paling sering dijumpai. Secara umum, alergi merupakan...",
            "penyakit peradangan dapat timbul akibat infeksi strep throat yang..."
        )
        descList = arrayOf(
            getString(R.string.Penyakit_diabetes),
            getString(R.string.Alergi_kacang),
            getString(R.string.Rematik))

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