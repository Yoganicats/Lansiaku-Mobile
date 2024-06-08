package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPembayaranKonsultasiBinding
import id.ac.ukdw.skripsilansiaku.databinding.ItemPilihDokterKonsultasiPembayaranBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Pembayaran_Konsultasi_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPembayaranKonsultasiBinding
    private lateinit var includeBinding: ItemPilihDokterKonsultasiPembayaranBinding
    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null
    private var oldDataJadwalMdl: Jadwal2Mdl? = null
    var totalHarga = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranKonsultasiBinding.inflate(layoutInflater)
        includeBinding =
            ItemPilihDokterKonsultasiPembayaranBinding.bind(binding.itemPilihDokterKonsultasiPembayaran.root)
        setContentView(binding.root)

        if (intent.extras != null) {
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(INTENT_PARAM1)
            oldDataJadwalMdl = bundle?.getParcelable(INTENT_PARAM2)
        }

        includeBinding.apply {
            imgDokterKonsultasi.setImageResource(
                oldDataClassDokterKonsultasi?.imageDokterKonsultasi ?: -1
            )
            tvNamaDokterKonsultasi.text = oldDataClassDokterKonsultasi?.namaDokterKonsultasi
            tvSpesialisKonsultasi.text = oldDataClassDokterKonsultasi?.spesialisDokterKonsultasi
            tvHargaDokterKonsultasi.text = oldDataClassDokterKonsultasi?.hargaDokterKonsultasi
            tvLamakerjaDokter.text = oldDataClassDokterKonsultasi?.dokterBekerja


            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, oldDataJadwalMdl!!.year)
            calendar.set(Calendar.MONTH, oldDataJadwalMdl!!.month - 1)
            calendar.set(Calendar.DAY_OF_MONTH, oldDataJadwalMdl!!.date)
            calendar.set(Calendar.HOUR_OF_DAY, oldDataJadwalMdl!!.hours)
            calendar.set(Calendar.MINUTE, oldDataJadwalMdl!!.minutes)
            calendar.set(Calendar.SECOND, 0)


            val dateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID"))
            val currentDate = dateFormat.format(calendar.time)

            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            val hariskg =  "$currentDate"
            tvHarikonsul.text = hariskg

            "$currentDate"

            val mulaiKonsulStr = "${LocalTime.of(oldDataJadwalMdl!!.hours, oldDataJadwalMdl!!.minutes).format(formatter)}"
            tvMulaiKonsultasi.text = mulaiKonsulStr
        }

        binding.apply {
            txtNamaBank.text = oldDataJadwalMdl?.metodePembyrn

            txtHargaSubTotal.text = oldDataClassDokterKonsultasi?.hargaDokterKonsultasi
            txtHargalayanan.text = if (oldDataJadwalMdl?.metodePembyrn != "") {
                "Rp ${Utils.BIAYA_CASHLESS}"
            } else {
                "Rp 0"
            }

            val hrgSubttlInt = moneyToInt(txtHargaSubTotal.text.toString())
            val hrgLynnInt = moneyToInt(txtHargalayanan.text.toString())
            totalHarga = hrgSubttlInt + hrgLynnInt

            txtHargaTotal.text = intToMoney(totalHarga)

            txtMetodePembayaran.setOnClickListener {
                val intent = Intent(
                    this@Pembayaran_Konsultasi_Activity,
                    Pilih_Metode_Pembayaran_Konsultasi_Activity::class.java
                )
                intent.putExtra(
                    Pilih_Metode_Pembayaran_Konsultasi_Activity.INTENT_PARAM1,
                    oldDataClassDokterKonsultasi
                )
                intent.putExtra(
                    Pilih_Metode_Pembayaran_Konsultasi_Activity.INTENT_PARAM2,
                    oldDataJadwalMdl
                )
                startActivity(intent)
            }

            lanjutBtn.setOnClickListener {
                if(txtHargalayanan.text.toString() == "Rp 0"){
                    Toast.makeText(this@Pembayaran_Konsultasi_Activity, "Silahkan memilih metode pembayaran terlebih dahulu", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val dataJadwalMdl = oldDataJadwalMdl?.copy(
                    totalByr = intToMoney(totalHarga)
                )

                val intent = Intent(
                    this@Pembayaran_Konsultasi_Activity,
                    Selesaikan_Pembayaran_Konsultasi_Activity::class.java
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

    companion object {
        const val INTENT_PARAM1 = "metodePembyrnKons1"
        const val INTENT_PARAM2 = "metodePembyrnKons2"
    }
}