package id.ac.ukdw.skripsilansiaku

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class ProfileActivity : AppCompatActivity() {


    lateinit var textfullname: TextView
    lateinit var textEmail: TextView
    private lateinit var database: DatabaseReference
    lateinit var btnLogout: TextView
    var firebaseAuth = FirebaseAuth.getInstance()

    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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



        database =
            Firebase.database("https://project-skripsi-lansiaku-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        textfullname = findViewById(R.id.fullname)
        textEmail = findViewById(R.id.txtEmail)
        btnLogout = findViewById(R.id.btnkeluar)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            textfullname.text = firebaseUser.displayName
            textEmail.text = firebaseUser.email
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        googleSignInClient =
            GoogleSignIn.getClient(this@ProfileActivity, GoogleSignInOptions.DEFAULT_SIGN_IN)

        btnLogout.setOnClickListener {

            googleSignInClient.signOut().addOnCompleteListener {
                googleSignInClient.signOut().addOnCompleteListener { task ->
                    database.child("DataPesanan").removeValue()
                    // Check condition
                    if (task.isSuccessful) {
                        // When task is successful sign out from firebase
                        firebaseAuth.signOut()
                        // Display Toast
                        Toast.makeText(
                            applicationContext,
                            "Anda Berhasil Keluar dari akun",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        // Finish activity
                        finish()
                    }
                }
            }


        }
    }
}