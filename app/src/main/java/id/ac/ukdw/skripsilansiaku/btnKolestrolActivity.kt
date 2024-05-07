package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.AdapterClassArtikel
import id.ac.ukdw.skripsilansiaku.dataclass.DataArtikelClass
import java.util.Locale

class btnKolestrolActivity : AppCompatActivity() {

    private lateinit var recyclerViewArtikel: RecyclerView
    private lateinit var dataList: ArrayList<DataArtikelClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var penjelasanList:Array<String>
    lateinit var kategoriList:Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClassArtikel
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataArtikelClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_btn_kolestrol)


        val btnKolestrol = findViewById<Button>(R.id.btn_Semua_artikel)
        btnKolestrol.setOnClickListener {
            startActivity(
                Intent(this, btnKolestrolActivity::class.java)
            )
        }
        val btnSemua = findViewById<Button>(R.id.btn_Semua_artikel)
        btnSemua.setOnClickListener {
            startActivity(
                Intent(this, ArtikelActivity::class.java)
            )
        }


        imageList = arrayOf(
            R.drawable.makanan_kolestrol,
            R.drawable.makan_telur,
            R.drawable.kolestrol_normal,
            R.drawable.enam_obatkoles)

        titleList = arrayOf(
            "Makanan Ini Identik dengan Kolesterol Jahat",
            "Ketahui Batas Penderita Kolesterol Makan Telur",
            "Pentingnya Mengetahui Tingkat Kolesterol Normal",
            "6 Obat Kolesterol untuk Menurunkan Kolesterol Tinggi")
        penjelasanList = arrayOf(
            "Kolestrol",
            "Kolestrol",
            "Kolestrol",
            "Kolestrol")


//      //Penjelasan
        descList = arrayOf(
            "Tingginya kadar kolesterol jahat bisa dipengaruhi oleh makanan yang kita konsumsi. Jika kadarnya tidak terkontrol,...",
            "Sebagian orang mungkin masih mempertanyakan, bolehkah penderita kolesterol makan telur.karena..",
            "Kadar kolesterol tinggi dapat meningkatkan risiko penyakit jantung, stroke, dan sirkulasi darah yang buruk. Mengetahui...",
            "Obat kolesterol biasanya digunakan ketika perbaikan pola hidup saja tidak berhasil untuk menurunkan kadar kolesterol...")
        //detailpenjelasan
        kategoriList = arrayOf(
            getString(R.string.makananIdentik),
            getString(R.string.telur),
            getString(R.string.kadar),
            getString(R.string.enamobat))
//


        recyclerViewArtikel = findViewById(R.id.rv_Artikel)
        searchView = findViewById(R.id.search)
        recyclerViewArtikel.layoutManager = LinearLayoutManager(this)
        recyclerViewArtikel.setHasFixedSize(true)

        dataList = arrayListOf<DataArtikelClass>()
        searchList = arrayListOf<DataArtikelClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitleArtikel.lowercase(Locale.getDefault()).contains(searchText)) {
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

    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataArtikelClass(imageList[i], titleList[i],descList[i],penjelasanList[i],kategoriList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerViewArtikel.adapter = AdapterClassArtikel(searchList)
    }
}