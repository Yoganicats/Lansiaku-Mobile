package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPembayaranBerhasilKonsulasiBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import java.util.UUID

class Pembayaran_Berhasil_Konsulasi_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPembayaranBerhasilKonsulasiBinding
    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null
    private var oldDataJadwalMdl: Jadwal2Mdl? = null

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBerhasilKonsulasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(INTENT_PARAM1)
            oldDataJadwalMdl = bundle?.getParcelable(INTENT_PARAM2)
        }

        saveDataToFirebase()
        binding.buttonPesan.setOnClickListener{
            val intent = Intent(this,PesanActivity::class.java)
            startActivity(intent)
        }

        binding.apply {

            totalRupiahbayar.text =
                oldDataJadwalMdl?.totalByr + " ( " + oldDataJadwalMdl?.metodePembyrn + " ) "

            berandaBtn.setOnClickListener {
                val intent =
                    Intent(this@Pembayaran_Berhasil_Konsulasi_Activity, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }
    }

    private fun saveDataToFirebase() {
        database =
            Firebase.database("https://project-skripsi-lansiaku-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        writeData()
    }

    private fun writeData() {
        val dokterJadwal = DokterJadwal(
            idDokterJadwal = UUID.randomUUID().toString(),
            dataClassDokterKonsultasi = oldDataClassDokterKonsultasi!!,
            jadwal2Mdl = oldDataJadwalMdl!!.copy(
                month = oldDataJadwalMdl!!.month + 1
            )
        )

        database.child("DataPesanan").child(dokterJadwal.idDokterJadwal).setValue(dokterJadwal)
    }

    companion object {
        const val INTENT_PARAM1 = "berhasilMembayar1"
        const val INTENT_PARAM2 = "berhasilMembayar2"
    }
}