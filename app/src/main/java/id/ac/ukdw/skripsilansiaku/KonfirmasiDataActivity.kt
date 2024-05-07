package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class KonfirmasiDataActivity : AppCompatActivity() {

    lateinit var textfullname: TextView
    var firebaseAuth = FirebaseAuth.getInstance()

    var oldDataConf: KonfirmasiMdl? = null
    var oldDataJadwal: Jadwal2Mdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_data)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@KonfirmasiDataActivity, PilihDokterActivity::class.java)
            )
        }

        val rsTv: TextView = findViewById(R.id.txtRumahSakit)
        val klinikTv = findViewById<TextView>(R.id.tv_klinik)
        val dokterTv: TextView = findViewById(R.id.tv_namaDokter)
        val waktuTv = findViewById<TextView>(R.id.txt_JamLayanan)
        val operasionalTv: TextView = findViewById(R.id.hariOperasional)
        val lnjtPmbyrnBtn: Button = findViewById(R.id.button)


        lnjtPmbyrnBtn.setOnClickListener {
            if(firebaseAuth.currentUser != null){
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val confData = KonfirmasiMdl(
                        oldDataConf?.rsData!!,
                        oldDataConf?.jnsData!!,
                        oldDataConf?.dokterData!!,
                        firebaseUser.displayName!!
                    )
                    val intent = Intent(this, PembayaranActivity::class.java)
                    intent.putExtra(Utils.CONFIRMATION_DATA, confData)
                    intent.putExtra(Utils.JADWAL_DATA, oldDataJadwal)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@KonfirmasiDataActivity, "PASIEN NULL !!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@KonfirmasiDataActivity, "Nama pasien tidak valid", Toast.LENGTH_SHORT).show()
            }
        }


        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Pilih_Jadwal_Temu_Dokter_2_Activity.INTENT_PARAM_1)
            oldDataJadwal = bundle?.getParcelable(Pilih_Jadwal_Temu_Dokter_2_Activity.INTENT_PARAM_2)

            rsTv.text = oldDataConf?.rsData?.dataTitle
            klinikTv.text = oldDataConf?.jnsData?.nameKlinik
            /*waktuTv.text = oldDataConf?.dokterData?.jamDokter
            operasionalTv.text = oldDataConf?.dokterData?.waktuDokter*/

            val dateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID"))

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, oldDataJadwal!!.year)
            calendar.set(Calendar.MONTH, oldDataJadwal!!.month)
            calendar.set(Calendar.DAY_OF_MONTH, oldDataJadwal!!.date)
            calendar.set(Calendar.HOUR_OF_DAY, oldDataJadwal!!.hours)
            calendar.set(Calendar.MINUTE, oldDataJadwal!!.minutes)

            val currentDate = dateFormat.format(calendar.time)

            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            waktuTv.text = currentDate
            operasionalTv.text = LocalTime.of(oldDataJadwal!!.hours, oldDataJadwal!!.minutes).format(formatter)
            dokterTv.text = oldDataConf?.dokterData?.namaDokter
        }

        textfullname = findViewById(R.id.fullname)
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            textfullname.text = firebaseUser.displayName

        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}