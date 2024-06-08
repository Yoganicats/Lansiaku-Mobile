package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import id.ac.ukdw.skripsilansiaku.databinding.ActivityKonfirmasiDataKonsultasiBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Konfirmasi_Data_Konsultasi_Activity : AppCompatActivity() {

    var firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityKonfirmasiDataKonsultasiBinding
    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null
    //private var oldDataJadwalMdl: JadwalMdl? = null
    private var oldDataJadwalMdl: Jadwal2Mdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfirmasiDataKonsultasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener {
            val intent = Intent(this, Pilih_Jadwal_Konsultasi_2_Activity::class.java)

            startActivity(intent)
        }
        if(intent.extras != null){
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(INTENT_PARAM1)
            oldDataJadwalMdl = bundle?.getParcelable(INTENT_PARAM2)
        }

        binding.apply {
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                fullname.text = firebaseUser.displayName
            }

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, oldDataJadwalMdl!!.year)
            calendar.set(Calendar.MONTH, oldDataJadwalMdl!!.month)
            calendar.set(Calendar.DAY_OF_MONTH, oldDataJadwalMdl!!.date)
            calendar.set(Calendar.HOUR_OF_DAY, oldDataJadwalMdl!!.hours)
            calendar.set(Calendar.MINUTE, oldDataJadwalMdl!!.minutes)
            calendar.set(Calendar.SECOND, 0)

            //tvJadwalHari.text = oldDataJadwalMdl?.waktu
            val dateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID"))
            val currentDate = dateFormat.format(calendar.time)

            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            tvHariKonsultasi.text = currentDate
            txtJamLayananKonsultasi.text = LocalTime.of(oldDataJadwalMdl!!.hours, oldDataJadwalMdl!!.minutes).format(formatter)
            tvNamaDokter.text = oldDataClassDokterKonsultasi?.namaDokterKonsultasi
            umum.text = oldDataClassDokterKonsultasi?.spesialisDokterKonsultasi

            lanjutBtn.setOnClickListener {
                val intent = Intent(
                    this@Konfirmasi_Data_Konsultasi_Activity,
                    Pembayaran_Konsultasi_Activity::class.java
                )
                intent.putExtra(Pembayaran_Konsultasi_Activity.INTENT_PARAM1, oldDataClassDokterKonsultasi)
                intent.putExtra(Pembayaran_Konsultasi_Activity.INTENT_PARAM2, oldDataJadwalMdl)
                startActivity(intent)
            }
        }
    }

    companion object{
        const val INTENT_PARAM1 = "konfDataKons1"
        const val INTENT_PARAM2 = "konfDataKons2"
    }
}