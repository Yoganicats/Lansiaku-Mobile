package id.ac.ukdw.skripsilansiaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    lateinit var btnGoogle: Button
    lateinit var progressDialog: ProgressDialog
    lateinit var googleSignInClient: GoogleSignInClient
    var firebaseAuth = FirebaseAuth.getInstance()

    companion object{
        private const val RC_SIGN_IN =3000
    }

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



        btnGoogle = findViewById(R.id.btnLoginGoogle)


        // proses login
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Sedang Masuk Ke Halaman Utama")
        progressDialog.setMessage("Silahkan tunggu")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1050080622798-05d020js9loqbd79loa0m3ljoi3qp63c.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode ==RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e:ApiException){
                e.printStackTrace()
                Toast.makeText(applicationContext, "Masukkan Akun Google Kembali", LENGTH_SHORT).show()
            }
        }
    }
    fun firebaseAuthWithGoogle(idToken:String){
        progressDialog.show()
        val credentian = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(credentian)
            .addOnSuccessListener {
                startActivity(Intent(this,HomeActivity::class.java))
            }
            .addOnFailureListener{ error ->
                Toast.makeText(this,"Anda Gagal Login", LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                progressDialog.dismiss()
            }
    }
}
