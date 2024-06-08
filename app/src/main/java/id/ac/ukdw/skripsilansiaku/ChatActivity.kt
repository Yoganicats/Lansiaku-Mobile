package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.skripsilansiaku.databinding.ActivityChatBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var oldDataDokterJadwal: DokterJadwal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this@ChatActivity, PesanActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataDokterJadwal = bundle?.getParcelable(INTENT_DATA)!!
        }

        binding.apply {
            fotoProfileIV.setImageResource(
                oldDataDokterJadwal.dataClassDokterKonsultasi.imageDokterKonsultasi
            )
            NamaDokterDetailKonsultasi.text = oldDataDokterJadwal.dataClassDokterKonsultasi.namaDokterKonsultasi
            NamaSpesialisDetailKonsultasi.text = oldDataDokterJadwal.dataClassDokterKonsultasi.spesialisDokterKonsultasi

            Log.e("ChatActivity","nama: ${NamaDokterDetailKonsultasi.text}")
            Log.e("ChatActivity","spesialis: ${NamaSpesialisDetailKonsultasi.text}")
        }

    }

    companion object{
        const val INTENT_DATA = "chatdata"
    }
}