package id.ac.ukdw.skripsilansiaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Intent

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: TextView
    lateinit var progressDialog: ProgressDialog


    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.btnEmail)
        edtPassword = findViewById(R.id.btnPassword)
        btnRegister = findViewById(R.id.dftrakun)
        btnLogin = findViewById(R.id.btnLogin)

        // proses login
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("logging")
        progressDialog.setMessage("Silahkan tunggu")

        btnLogin.setOnClickListener {
            if(edtEmail.text.isNotEmpty() && edtPassword.text.isNotEmpty()){
                proseslogin()
            }else{
                Toast.makeText(this, "Silahkan masukkan semua data terlebih dahulu",LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }
    private fun proseslogin(){
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener{ error ->
                Toast.makeText(this,"USERNAME ATAU PASSOWORD YANG ANDA MASUKAN SALAH", LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                progressDialog.dismiss()
            }
    }
}
