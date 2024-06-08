package id.ac.ukdw.skripsilansiaku


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import id.ac.ukdw.skripsilansiaku.adapter.ViewPagerAdapter



class HomeActivity : AppCompatActivity() {

    lateinit var textfullname: TextView

    var firebaseAuth = FirebaseAuth.getInstance()

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val btntemudokter = findViewById<ImageButton>(R.id.btnTemuDokter)
        btntemudokter.setOnClickListener {
            startActivity(
                Intent(this, TemudokterActivity::class.java)
            )
        }
        val btnartikel = findViewById<ImageButton>(R.id.btnArtikel)
        btnartikel.setOnClickListener {
            startActivity(
                Intent(this, ArtikelActivity::class.java)
            )
        }
        val btnInfoPenyakit = findViewById<ImageButton>(R.id.btnInfoPenyakit)
        btnInfoPenyakit.setOnClickListener {
            startActivity(
                Intent(this, Info_PenyakitActivity::class.java)
            )
        }
        val btnInfoObat = findViewById<ImageButton>(R.id.btnInfoObat)
        btnInfoObat.setOnClickListener {
            startActivity(
                Intent(this, Info_ObatActivity::class.java)
            )
        }
        val btnKonsultasi = findViewById<ImageButton>(R.id.btnKosultasi)
        btnKonsultasi.setOnClickListener {
            startActivity(
                Intent(this, KonsultasiActivity::class.java)
            )
        }

        textfullname = findViewById(R.id.fullname)
//        textEmail = findViewById(R.id.txtEmail)

        viewPager = findViewById(R.id.idViewPager)


        // menambahkan data gamabar
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.slider_pertama
        imageList = imageList + R.drawable.slider_kedua
        imageList = imageList + R.drawable.slider_ketiga
        imageList = imageList + R.drawable.slider_pertama

        // menginisialisasi tampilan, adaptor pager dan menambahkan daftar gambar
        viewPagerAdapter = ViewPagerAdapter(this@HomeActivity, imageList)

        // adapter untuk mengatur pada tampilan
        viewPager.adapter = viewPagerAdapter


        //NAMPILIN PROFIL
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            textfullname.text = firebaseUser.displayName
//            textEmail.text = firebaseUser.email
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

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
}