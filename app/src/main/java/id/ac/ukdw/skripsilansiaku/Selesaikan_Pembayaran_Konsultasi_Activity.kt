package id.ac.ukdw.skripsilansiaku

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPembayaranBerhasilKonsulasiBinding
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPilihJadwalKonsultasi2Binding
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPilihJadwalTemuDokterBinding
import id.ac.ukdw.skripsilansiaku.databinding.ActivitySelesaikanPembayaranKonsultasiBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl

class Selesaikan_Pembayaran_Konsultasi_Activity : AppCompatActivity() {


    private lateinit var binding: ActivitySelesaikanPembayaranKonsultasiBinding


    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null
    private var oldDataJadwalMdl: Jadwal2Mdl? = null

    var totalHarga = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelesaikanPembayaranKonsultasiBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var clipData: ClipData
        val copyTxt = findViewById<TextView>(R.id.txtCopy)
        // Action when the copy button is clicked
        binding.btnCopy.setOnClickListener {

            // Text from the edit text is stored in a val
            val txtCopy = copyTxt!!.text.toString()

            // clip data is initialized with the text variable declared above
            clipData = ClipData.newPlainText("text", txtCopy)

            // Clipboard saves this clip object
            clipboardManager.setPrimaryClip(clipData)

            // A toast is shown for user reference that the text is copied to the clipboard
            Toast.makeText(applicationContext, "Disalin ke Clipboard", Toast.LENGTH_SHORT).show()
        }
        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(
                Pembayaran_Berhasil_Konsulasi_Activity.INTENT_PARAM1
            )
            oldDataJadwalMdl = bundle?.getParcelable(Pembayaran_Berhasil_Konsulasi_Activity.INTENT_PARAM2)
        }
        binding.apply {

            txtmetodepembayaran.text =
                "  " + oldDataJadwalMdl?.metodePembyrn + "  "


        }
        binding.apply {

            totalRupiahbayar.text =
                "  " + oldDataJadwalMdl?.totalByr + "  "


        }
        binding.buttonKonfirPembayaran.setOnClickListener {


            val dataJadwalMdl = oldDataJadwalMdl?.copy(
                totalByr = intToMoney(totalHarga)
            )

            val intent = Intent(
                this@Selesaikan_Pembayaran_Konsultasi_Activity,
                Pembayaran_Berhasil_Konsulasi_Activity::class.java
            )
            intent.putExtra(
                Pembayaran_Berhasil_Konsulasi_Activity.INTENT_PARAM1,
                oldDataClassDokterKonsultasi
            )
            intent.putExtra(
                Pembayaran_Berhasil_Konsulasi_Activity.INTENT_PARAM2,
                dataJadwalMdl
            )
            startActivity(intent)
        }
    }



}
