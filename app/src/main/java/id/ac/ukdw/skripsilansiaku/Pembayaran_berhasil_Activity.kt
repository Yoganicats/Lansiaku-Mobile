package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl
import java.util.UUID

class Pembayaran_berhasil_Activity : AppCompatActivity() {

    var oldDataConf: KonfirmasiMdl? = null
    var oldDataJadwal: Jadwal2Mdl? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_berhasil)

        FirebaseApp.initializeApp(this)


        val berandaPesanan = findViewById<Button>(R.id.buttonPesan)
        berandaPesanan.setOnClickListener {
            val intent = Intent(this, PesanActivity::class.java)
            startActivity(intent)
        }

        val berandaBtn = findViewById<Button>(R.id.buttonberanda)
        berandaBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        val ttlRupiahBayar = findViewById<TextView>(R.id.totalRupiahbayar)

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
            oldDataJadwal = bundle?.getParcelable(Utils.JADWAL_DATA)
            val total = oldDataConf?.totalBiaya ?: 0
            val totalStr = intToMoney(total)
            ttlRupiahBayar.text = totalStr + " ( " + oldDataConf?.metodePembayaran + " ) "
        }

        saveDataToFirebase()

    }


    private fun saveDataToFirebase() {
        database = Firebase.database("https://project-skripsi-lansiaku-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        writeData()
    }

    private fun writeData() {
        val dokterJadwal = DokterJadwal(
            idDokterJadwal = UUID.randomUUID().toString(),
            dataKonfirmasi = oldDataConf!!,
            jadwal2Mdl = oldDataJadwal!!.copy(
                month = oldDataJadwal!!.month + 1
            )
        )

        database.child("DataPesanan").child(dokterJadwal.idDokterJadwal).setValue(dokterJadwal)
    }

}