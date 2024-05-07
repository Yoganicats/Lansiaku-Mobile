package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.ac.ukdw.skripsilansiaku.Utils.BIAYA_CASHLESS
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import id.ac.ukdw.skripsilansiaku.dataclass.KonfirmasiMdl
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class PembayaranActivity : AppCompatActivity() {

    var oldDataConf: KonfirmasiMdl? = null
    var oldDataJadwal: Jadwal2Mdl? = null
    var totalHarga: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        val imgItemDokter = findViewById<ImageView>(R.id.img_item_dokter)
        val tvNmDokterTxt = findViewById<TextView>(R.id.tv_namaDokter)
        val tvSpecialisTxt = findViewById<TextView>(R.id.tv_spesialis)
        val tvHargaTxt = findViewById<TextView>(R.id.tv_harga)
        val operasionalTxt = findViewById<TextView>(R.id.tv_harilayanan)
        val tvJamTxt = findViewById<TextView>(R.id.tv_jam)


        val hrgSubTtlTxt = findViewById<TextView>(R.id.txt_hargaSubTotal)
        val hrgLynnTtlTxt = findViewById<TextView>(R.id.txt_hargalayanan)
        val hrgTtlTxt = findViewById<TextView>(R.id.txt_hargaTotal)

        val namaBankTxt = findViewById<TextView>(R.id.txt_namaBank)
        val metodePembayaranTxt = findViewById<TextView>(R.id.txtMetode_Pembayaran)
        val lanjutBtn = findViewById<Button>(R.id.button2)


        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataConf = bundle?.getParcelable(Utils.CONFIRMATION_DATA)
            oldDataJadwal = bundle?.getParcelable(Utils.JADWAL_DATA)

            if (oldDataConf?.dokterData?.imageDokter != null) {
                val imgDokter = oldDataConf?.dokterData?.imageDokter ?: -1
                imgItemDokter.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@PembayaranActivity,
                        imgDokter
                    )
                )
            }

            tvNmDokterTxt.text = oldDataConf?.dokterData?.namaDokter
            tvSpecialisTxt.text = oldDataConf?.dokterData?.namaSpesialis
            tvHargaTxt.text = oldDataConf?.dokterData?.hargaDokter

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, oldDataJadwal!!.year)
            calendar.set(Calendar.MONTH, oldDataJadwal!!.month - 1)
            calendar.set(Calendar.DAY_OF_MONTH, oldDataJadwal!!.date)
            calendar.set(Calendar.HOUR_OF_DAY, oldDataJadwal!!.hours)
            calendar.set(Calendar.MINUTE, oldDataJadwal!!.minutes)
            calendar.set(Calendar.SECOND, 0)

            val dateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID"))
            val currentDate = dateFormat.format(calendar.time)

            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            /*operasionalTxt.text = oldDataConf?.dokterData?.waktuDokter
            tvJamTxt.text = oldDataConf?.dokterData?.jamDokter*/

            operasionalTxt.text = currentDate
            tvJamTxt.text = LocalTime.of(oldDataJadwal!!.hours, oldDataJadwal!!.minutes).format(formatter)

            hrgSubTtlTxt.text = oldDataConf?.dokterData?.hargaDokter
            hrgLynnTtlTxt.text = if (oldDataConf?.metodePembayaran != "") {
                "Rp $BIAYA_CASHLESS"
            } else {
                "Rp 0"
            }

            val hrgSubttlInt = moneyToInt(tvHargaTxt.text.toString())
            val hrgLynnInt = moneyToInt(hrgLynnTtlTxt.text.toString())
            totalHarga = hrgSubttlInt + hrgLynnInt

            hrgTtlTxt.text = intToMoney(totalHarga)

            namaBankTxt.text = oldDataConf?.metodePembayaran
        }

        metodePembayaranTxt.setOnClickListener {
            val confData = KonfirmasiMdl(
                oldDataConf?.rsData!!,
                oldDataConf?.jnsData!!,
                oldDataConf?.dokterData!!,
                oldDataConf?.pasienData!!,
                oldDataConf?.metodePembayaran!!
            )
            val intent = Intent(this, Pilih_Metode_Pembayaran_Activity::class.java)

            intent.putExtra(Utils.CONFIRMATION_DATA, confData)
            intent.putExtra(Utils.JADWAL_DATA, oldDataJadwal)

            startActivity(intent)
        }

        lanjutBtn.setOnClickListener {
            if(hrgLynnTtlTxt.text.toString() == "Rp 0"){
                Toast.makeText(this@PembayaranActivity, "Silahkan memilih metode pembayaran terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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