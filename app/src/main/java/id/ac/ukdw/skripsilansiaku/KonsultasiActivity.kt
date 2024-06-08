package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
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
        clicListener();
        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }


    }
    public fun clicListener(){
        var imageparu = findViewById<ImageView>(R.id.btnParu)

        imageparu.setOnClickListener{
            paru()
        }

    }

    public fun paru(){

        val intent = Intent(this@KonsultasiActivity, Pilih_Dokter_KonsultasiActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)



    }


}