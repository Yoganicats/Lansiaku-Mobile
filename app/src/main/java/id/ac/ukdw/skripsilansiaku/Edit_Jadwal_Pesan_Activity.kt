package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.skripsilansiaku.databinding.ActivityEditJadwalPesanBinding
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPilihJadwalKonsultasi2Binding
import id.ac.ukdw.skripsilansiaku.dataclass.Jadwal2Mdl
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class Edit_Jadwal_Pesan_Activity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityEditJadwalPesanBinding
    private lateinit var firebaseId: String
    private lateinit var jadwal2Mdl: Jadwal2Mdl

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditJadwalPesanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database =
            Firebase.database("https://project-skripsi-lansiaku-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        if (intent.extras != null) {
            val bundle = intent.extras
            firebaseId = bundle?.getString(INTENT_PARAM)!!
            jadwal2Mdl = bundle.getParcelable(INTENT_PARAM_2)!!
        }

        binding.apply {
            timePicker.setIs24HourView(true)

            calendar.set(Calendar.YEAR, jadwal2Mdl.year)
            calendar.set(Calendar.MONTH, jadwal2Mdl.month - 1)
            calendar.set(Calendar.DAY_OF_MONTH, jadwal2Mdl.date)
            calendar.set(Calendar.HOUR_OF_DAY, jadwal2Mdl.hours)
            calendar.set(Calendar.MINUTE, jadwal2Mdl.minutes)
            calendar.set(Calendar.SECOND, 0)

            updateCurrentDateTime()

            // Set tanggal pada DatePicker
            datePicker.init(
                jadwal2Mdl.year,
                jadwal2Mdl.month - 1,
                jadwal2Mdl.date
            ) { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)

                val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale("id", "ID"))
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))
                val currentDate = dateFormat.format(calendar.time)
                val dayOfWeek = dayOfWeekFormat.format(calendar.time)
                val selectedDateStr = "$dayOfWeek, $currentDate"
                dateText.text = selectedDateStr

            }

            timePicker.hour = jadwal2Mdl.hours
            timePicker.minute = jadwal2Mdl.minutes

            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val timeStr = String.format(Locale("id", "ID"), "%02d:%02d", hourOfDay, minute)
                timeText.text = timeStr
            }

            lanjutBtn.setOnClickListener {
                val dataJadwalMdl = Jadwal2Mdl(
                    year = calendar.get(Calendar.YEAR),
                    month = calendar.get(Calendar.MONTH) + 1,
                    date = calendar.get(Calendar.DATE),
                    hours = calendar.get(Calendar.HOUR_OF_DAY),
                    minutes = calendar.get(Calendar.MINUTE),
                    metodePembyrn = jadwal2Mdl.metodePembyrn,
                    totalByr = jadwal2Mdl.totalByr
                )

                database
                    .child("DataPesanan")
                    .child(firebaseId)
                    .updateChildren(mapOf("jadwal2Mdl" to dataJadwalMdl))
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@Edit_Jadwal_Pesan_Activity,
                            "Berhasil Mengubah Jam atau Tanggal.",
                            Toast.LENGTH_SHORT
                        )
                            .show().also {
                                val intent = Intent(
                                    this@Edit_Jadwal_Pesan_Activity,
                                    PesanActivity::class.java
                                )
                                startActivity(intent)
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this@Edit_Jadwal_Pesan_Activity,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    private fun updateCurrentDateTime() {
        binding.apply {
            // Update date TextView
            val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale("id", "ID"))
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale("id", "ID"))
            val currentDate = dateFormat.format(calendar.time)
            val dayOfWeek = dayOfWeekFormat.format(calendar.time)

            val selectedDateStr = "$dayOfWeek, $currentDate"
            dateText.text = selectedDateStr

            val selectedTimes = LocalTime.of(jadwal2Mdl.hours, jadwal2Mdl.minutes)

            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            timeText.text = selectedTimes.format(formatter)


        }
    }

    companion object {
        const val INTENT_PARAM = "firebaseId"
        const val INTENT_PARAM_2 = "jadwalFirebase"
    }
}