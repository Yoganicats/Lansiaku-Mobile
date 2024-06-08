package id.ac.ukdw.skripsilansiaku

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl

class Selesaikan_Pembayaran_Activity : AppCompatActivity() {

    var oldDataConf: KonfirmasiMdl? = null
    var oldDataJadwal: Jadwal2Mdl? = null
    var totalHarga: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_selesaikan_pembayaran)
        // Declaring the edit text and button from the layout file
        val copyTxt = findViewById<TextView>(R.id.txtCopy)
        val copyBtn = findViewById<Button>(R.id.btnCopy)
        val konfirBtn = findViewById<Button>(R.id.buttonKonfirPembayaran)

        // Initializing the ClipboardManager and Clip data
        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var clipData: ClipData

        // Action when the copy button is clicked
        copyBtn.setOnClickListener {

            // Text from the edit text is stored in a val
            val txtCopy = copyTxt!!.text.toString()

            // clip data is initialized with the text variable declared above
            clipData = ClipData.newPlainText("text", txtCopy)

            // Clipboard saves this clip object
            clipboardManager.setPrimaryClip(clipData)

            // A toast is shown for user reference that the text is copied to the clipboard
            Toast.makeText(applicationContext, "Disalin ke Clipboard", Toast.LENGTH_SHORT).show()
        }
        val Metodepembayaran = findViewById<TextView>(R.id.txtmetodepembayaran)
        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
            oldDataJadwal = bundle?.getParcelable(Utils.JADWAL_DATA)
            val total = oldDataConf?.totalBiaya ?: 0
            val totalStr = intToMoney(total)
            Metodepembayaran.text = oldDataConf?.metodePembayaran
        }

        val ttlRupiahBayar = findViewById<TextView>(R.id.totalRupiahbayar)

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
            oldDataJadwal = bundle?.getParcelable(Utils.JADWAL_DATA)
            val total = oldDataConf?.totalBiaya ?: 0
            val totalStr = intToMoney(total)
            ttlRupiahBayar.text = totalStr
        }


        konfirBtn.setOnClickListener {
            val confData = KonfirmasiMdl(
                oldDataConf?.rsData!!,
                oldDataConf?.jnsData!!,
                oldDataConf?.dokterData!!,
                oldDataConf?.pasienData!!,

                oldDataConf?.metodePembayaran!!,
                totalHarga

            )
            val intent = Intent(this, Pembayaran_berhasil_Activity::class.java)

            intent.putExtra(Utils.CONFIRMATION_DATA, confData)
            intent.putExtra(Utils.JADWAL_DATA, oldDataJadwal)

            startActivity(intent)
        }


    }
}