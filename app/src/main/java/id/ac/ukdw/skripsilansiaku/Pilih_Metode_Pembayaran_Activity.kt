package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import id.ac.ukdw.skripsilansiaku.Utils.CASHLESS
import id.ac.ukdw.skripsilansiaku.dataclass.Dokterdata
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl
import id.ac.ukdw.skripsilansiaku.dataclass.NamaDokterData

class Pilih_Metode_Pembayaran_Activity : AppCompatActivity() {

    var oldDataConf: KonfirmasiMdl? = null
    var oldJadwalData: Jadwal2Mdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_metode_pembayaran)

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
            oldJadwalData = bundle?.getParcelable(Utils.JADWAL_DATA)
        }

        val bniTxt = findViewById<TextView>(R.id.bniTxt)
        val bcaTxt = findViewById<TextView>(R.id.bcaTxt)
        val briTxt = findViewById<TextView>(R.id.briTxt)

        val gopayTxt = findViewById<TextView>(R.id.gopayTxt)
        val danaTxt = findViewById<TextView>(R.id.danaTxt)
        val ovoTxt = findViewById<TextView>(R.id.ovoTxt)

        bniTxt.setOnClickListener {
            goToPembayaran(CASHLESS[0])
        }

        bcaTxt.setOnClickListener {
            goToPembayaran(CASHLESS[1])
        }

        briTxt.setOnClickListener {
            goToPembayaran(CASHLESS[2])
        }

        gopayTxt.setOnClickListener {
            goToPembayaran(CASHLESS[3])
        }

        danaTxt.setOnClickListener {
            goToPembayaran(CASHLESS[4])
        }

        ovoTxt.setOnClickListener {
            goToPembayaran(CASHLESS[5])
        }

    }

    fun goToPembayaran(data: String){

        val confData = KonfirmasiMdl(
            oldDataConf?.rsData!!,
            oldDataConf?.jnsData!!,
            oldDataConf?.dokterData!!,
            oldDataConf?.pasienData!!,
            data
        )
        val intent = Intent(this, PembayaranActivity::class.java)

        intent.putExtra(Utils.CONFIRMATION_DATA, confData)
        intent.putExtra(Utils.JADWAL_DATA, oldJadwalData)

        startActivity(intent).also { finish() }
    }
}