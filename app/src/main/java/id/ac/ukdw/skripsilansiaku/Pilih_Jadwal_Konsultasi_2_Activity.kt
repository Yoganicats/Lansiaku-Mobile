package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.skripsilansiaku.Konfirmasi_Data_Konsultasi_Activity.Companion.INTENT_PARAM1
import id.ac.ukdw.skripsilansiaku.Konfirmasi_Data_Konsultasi_Activity.Companion.INTENT_PARAM2
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPilihJadwalKonsultasi2Binding
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Pilih_Jadwal_Konsultasi_2_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPilihJadwalKonsultasi2Binding
    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihJadwalKonsultasi2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.extras != null){
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(INTENT_PARAM)
        }

        updateCurrentDateTime()

        binding.apply {

            timePicker.setIs24HourView(true)
            datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale("id", "ID"))
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))
                val currentDate = dateFormat.format(calendar.time)
                val dayOfWeek = dayOfWeekFormat.format(calendar.time)
                val selectedDateStr = "$dayOfWeek, $currentDate"
                dateText.text = selectedDateStr

            }

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val timeStr = String.format(Locale("id", "ID"), "%02d:%02d", hourOfDay, minute)
                timeText.text = timeStr
            }

            lanjutBtn.setOnClickListener {
                val dataJadwalMdl = Jadwal2Mdl(
                    year = calendar.get(Calendar.YEAR),
                    month = calendar.get(Calendar.MONTH),
                    date = calendar.get(Calendar.DATE),
                    hours = calendar.get(Calendar.HOUR_OF_DAY),
                    minutes = calendar.get(Calendar.MINUTE)
                )

                val intent = Intent(this@Pilih_Jadwal_Konsultasi_2_Activity, Konfirmasi_Data_Konsultasi_Activity::class.java)
                intent.putExtra(INTENT_PARAM1, oldDataClassDokterKonsultasi)
                intent.putExtra(INTENT_PARAM2, dataJadwalMdl)
                startActivity(intent)
            }
        }
    }

    private fun updateCurrentDateTime() {
        binding.apply {
            // Update date TextView
            val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale("id", "ID"))
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))
            val currentDate = dateFormat.format(Calendar.getInstance().time)
            val dayOfWeek = dayOfWeekFormat.format(calendar.time)
            val selectedDateStr = "$dayOfWeek, $currentDate"
            dateText.text = selectedDateStr

            val selectedTimes = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            timeText.text = selectedTimes.format(formatter)
        }
    }

    companion object{
        const val INTENT_PARAM = "pilihjadwalkonsultasi"
    }
}