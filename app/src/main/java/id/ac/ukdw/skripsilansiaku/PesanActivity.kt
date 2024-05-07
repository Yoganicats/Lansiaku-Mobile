package id.ac.ukdw.skripsilansiaku

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.skripsilansiaku.adapter.PesanAdapter
import id.ac.ukdw.skripsilansiaku.databinding.ActivityPesanBinding
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class PesanActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityPesanBinding
    private var listDokterJadwal: ArrayList<DokterJadwal> = ArrayList()
    private lateinit var pesanAdapter: PesanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@PesanActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        FirebaseApp.initializeApp(this)

        saveDataToFirebase()

        //Variable navigation bar
        val btnHome = findViewById<Button>(R.id.button_Home)
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        val btnPesan = findViewById<Button>(R.id.button_Pesan)
        btnPesan.setOnClickListener {
            startActivity(
                Intent(this, PesanActivity::class.java)
            )
        }
        val btnRiwayat = findViewById<Button>(R.id.button_Riwayat)
        btnRiwayat.setOnClickListener {
            startActivity(
                Intent(this, RiwayatActivity::class.java)
            )
        }
        val btnProfiles = findViewById<Button>(R.id.button_Profile)
        btnProfiles.setOnClickListener {
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

                        if (LocalDateTime.now() <= LocalDateTime.of(
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
                    pesanAdapter = PesanAdapter(
                        listDokterJadwal
                    )

                    binding.rvPesan.adapter = pesanAdapter

                    pesanAdapter.onItemClick = {
                        if (it.dataClassDokterKonsultasi.namaDokterKonsultasi != "") {
                            // konsultasi

                            val jadwalSebenarnya = LocalTime.of(
                                it.jadwal2Mdl.hours,
                                it.jadwal2Mdl.minutes
                            )
                            if (LocalDateTime.now() >= LocalDateTime.of(
                                    LocalDate.of(
                                        it.jadwal2Mdl.year,
                                        it.jadwal2Mdl.month,
                                        it.jadwal2Mdl.date
                                    ),
                                    jadwalSebenarnya
                                ) && LocalDateTime.now() <= LocalDateTime.of(
                                    LocalDate.of(
                                        it.jadwal2Mdl.year,
                                        it.jadwal2Mdl.month,
                                        it.jadwal2Mdl.date
                                    ),
                                    jadwalSebenarnya.plusMinutes(10)
                                )
                            ) {
                                val intent = Intent(this@PesanActivity, ChatActivity::class.java)
                                intent.putExtra(ChatActivity.INTENT_DATA, it)
                                startActivity(
                                    intent
                                )
                            }
                        }
                    }

                    pesanAdapter.onBatalClick = {
                        showCancelOrderDialog(this@PesanActivity) {
                            database
                                .child("DataPesanan")
                                .child(it.idDokterJadwal)
                                .updateChildren(mapOf("batalState" to true))
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        this@PesanActivity,
                                        "Update successful.",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this@PesanActivity,
                                        it.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
                    pesanAdapter.onAturClick = {
                        val intent =
                            Intent(this@PesanActivity, Edit_Jadwal_Pesan_Activity::class.java)
                        intent.putExtra(Edit_Jadwal_Pesan_Activity.INTENT_PARAM, it.idDokterJadwal)
                        intent.putExtra(Edit_Jadwal_Pesan_Activity.INTENT_PARAM_2, it.jadwal2Mdl)
                        startActivity(intent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("PesanActivity", "Error getting data", error.toException())
            }
        })
    }

    fun showCancelOrderDialog(context: Context, onCancel: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.apply {
            setTitle("Pesan Pembatalan")
            setMessage("Apakah Anda ingin membatalkan pesanan ini?")
            setPositiveButton("Ya") { dialog, _ ->
                onCancel.invoke()
                dialog.dismiss()
            }
            setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}