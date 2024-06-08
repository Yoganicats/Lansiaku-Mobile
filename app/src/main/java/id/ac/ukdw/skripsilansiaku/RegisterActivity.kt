package id.ac.ukdw.skripsilansiaku

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest


class RegisterActivity : AppCompatActivity() {
    lateinit var edtFullname: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtPasswordConf: EditText
    lateinit var btnRegister: Button
    lateinit var btnLogin: TextView

    lateinit var progressDialog: ProgressDialog

    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edtFullname = findViewById(R.id.fullname)
        edtEmail = findViewById(R.id.btnEmail)
        edtPassword = findViewById(R.id.btnPassword)

        btnRegister = findViewById(R.id.btnDaftar)
        btnLogin = findViewById(R.id.Dftrakun)

        // proses daftar
        progressDialog = ProgressDialog(this)

        progressDialog.setMessage("Silahkan tunggu")

        btnLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
        btnRegister.setOnClickListener {
            if (edtFullname.text.isNotEmpty() && edtEmail.text.isNotEmpty() && edtPassword.text.isNotEmpty()) {
                if (edtPassword.text.toString() == edtPassword.text.toString()) {
                    //munculkan register
                    prosesRegister()

                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)

                }

                else {


                    Toast.makeText(
                        this, "Silahkan Masukkan Kata Sandi Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(this, "Silahkan masukkan semua data terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun prosesRegister(){
        val fullname = edtFullname.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = fullname
                    }
                    val user = task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnSuccessListener(){
                            progressDialog.dismiss()
//                            startActivity(
//                                Intent(this@RegisterActivity,LoginActivity::class.java))


                        }
                        .addOnFailureListener{error2->
                            Toast.makeText(this,error2.localizedMessage, Toast.LENGTH_SHORT).show()


                        }
                }else{
                    progressDialog.dismiss()
                }
            }
            .addOnFailureListener{ error->
                Toast.makeText(this,error.localizedMessage, Toast.LENGTH_SHORT).show()
            }



    }
}
