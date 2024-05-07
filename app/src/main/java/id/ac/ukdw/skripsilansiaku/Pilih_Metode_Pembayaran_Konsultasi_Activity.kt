package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPilihMetodePembayaranKonsultasiBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl

class Pilih_Metode_Pembayaran_Konsultasi_Activity : AppCompatActivity() {

    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null
    private var oldDataJadwalMdl: Jadwal2Mdl? = null

    private lateinit var binding: ActivityPilihMetodePembayaranKonsultasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihMetodePembayaranKonsultasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(INTENT_PARAM1)
            oldDataJadwalMdl = bundle?.getParcelable(INTENT_PARAM2)
        }

        binding.apply {
            bniTxt.setOnClickListener {
                goToPembayaran(Utils.CASHLESS[0])
            }

            bcaTxt.setOnClickListener {
                goToPembayaran(Utils.CASHLESS[1])
            }

            briTxt.setOnClickListener {
                goToPembayaran(Utils.CASHLESS[2])
            }

            gopayTxt.setOnClickListener {
                goToPembayaran(Utils.CASHLESS[3])
            }

            danaTxt.setOnClickListener {
                goToPembayaran(Utils.CASHLESS[4])
            }

            ovoTxt.setOnClickListener {
                goToPembayaran(Utils.CASHLESS[5])
            }
        }

    }

    fun goToPembayaran(data: String){
        val dataJadwalMdl = Jadwal2Mdl(
            year =  oldDataJadwalMdl?.year!!,
            month =  oldDataJadwalMdl?.month!!,
            date =  oldDataJadwalMdl?.date!!,
            hours = oldDataJadwalMdl?.hours!!,
            minutes = oldDataJadwalMdl?.minutes!!,
            metodePembyrn = data
        )
        val intent = Intent(
            this@Pilih_Metode_Pembayaran_Konsultasi_Activity,
            Pembayaran_Konsultasi_Activity::class.java
        )
        intent.putExtra(Pembayaran_Konsultasi_Activity.INTENT_PARAM1, oldDataClassDokterKonsultasi)
        intent.putExtra(Pembayaran_Konsultasi_Activity.INTENT_PARAM2, dataJadwalMdl)
        startActivity(intent).also { finish() }
    }

    companion object{
        const val INTENT_PARAM1 = "PmbyrnKonsutasi1"
        const val INTENT_PARAM2 = "PmbyrnKonsutasi2"
    }
}