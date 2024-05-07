package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.adapter.AdapterDaftarSpesialis
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassSpesialis

class KonsultasiActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerViewSpesialisAdapter: AdapterDaftarSpesialis? = null
    private var spesialisList = mutableListOf<DataClassSpesialis>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konsultasi)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        spesialisList = ArrayList()

        recyclerView = findViewById<View>(R.id.rvSpesialisLists) as RecyclerView
        recyclerViewSpesialisAdapter = AdapterDaftarSpesialis(this@KonsultasiActivity,spesialisList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewSpesialisAdapter

        recyclerViewSpesialisAdapter?.onItemClick= {
            val intent = Intent(this, Pilih_Dokter_KonsultasiActivity::class.java)

            startActivity(intent)
        }


        prepareMovieListData()


    }
    private fun prepareMovieListData() {
        var spesialis = DataClassSpesialis("Paru-Paru",R.drawable.paru)
        spesialisList.add(spesialis)

        spesialis = DataClassSpesialis("Ginjal",R.drawable.ginjal)
        spesialisList.add(spesialis)
        spesialis = DataClassSpesialis("Gigi",R.drawable.gigi)
        spesialisList.add(spesialis)
        spesialis = DataClassSpesialis("Jantung",R.drawable.jantung)
        spesialisList.add(spesialis)

        spesialis = DataClassSpesialis("Telinga",R.drawable.telinga)
        spesialisList.add(spesialis)
        spesialis = DataClassSpesialis("Sendi",R.drawable.sendi)
        spesialisList.add(spesialis)
        spesialis = DataClassSpesialis("lambung",R.drawable.lambung)
        spesialisList.add(spesialis)
        spesialis = DataClassSpesialis("Usus",R.drawable.usus)
        spesialisList.add(spesialis)



        recyclerViewSpesialisAdapter!!.notifyDataSetChanged()


    }
}