package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.skripsilansiaku.adapter.RiwayatAdapter
import id.ac.ukdw.skripsilansiaku.databinding.ActivityRiwayatBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class RiwayatActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityRiwayatBinding
    private var listDokterJadwal: ArrayList<DokterJadwal> = ArrayList()
    private lateinit var riwayatAdapter: RiwayatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backbtn.setOnClickListener {
            val intent = Intent(this@RiwayatActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@RiwayatActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        saveDataToFirebase()

        //Variable navigation bar
        binding.buttonHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        binding.buttonPesan.setOnClickListener {
            startActivity(
                Intent(this, PesanActivity::class.java)
            )
        }
        binding.buttonRiwayat.setOnClickListener {
            startActivity(
                Intent(this, RiwayatActivity::class.java)
            )
        }

        binding.buttonProfile.setOnClickListener {
            startActivity(
                Intent(this, ProfileActivity::class.java)
            )
        }

    }

    private fun saveDataToFirebase() {
        database =
            Firebase.database("https://project-skripsi-lansiaku-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        readData()
    }

    private fun readData() {
        database.child("DataPesanan").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listDokterJadwal.clear()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        //val keyItem = dataSnapshot.key
                        val dokterJadwal: DokterJadwal =
                            dataSnapshot.getValue(DokterJadwal::class.java)!!
                        val jadwalSebenarnya = LocalTime.of(
                            dokterJadwal.jadwal2Mdl.hours,
                            dokterJadwal.jadwal2Mdl.minutes
                        )

                        if (LocalDateTime.now() > LocalDateTime.of(
                                LocalDate.of(
                                    dokterJadwal.jadwal2Mdl.year,
                                    dokterJadwal.jadwal2Mdl.month,
                                    dokterJadwal.jadwal2Mdl.date
                                ),
                                jadwalSebenarnya.plusMinutes(10)
                            )
                        ) {
                            listDokterJadwal.add(dokterJadwal)
                        }
                    }
                    riwayatAdapter = RiwayatAdapter(
                        listDokterJadwal
                    )

                    binding.rvRiwayat.adapter = riwayatAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("RiwayatActivity", "Error getting data", error.toException())
            }
        })
    }
}