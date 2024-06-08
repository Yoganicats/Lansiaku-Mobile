package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageButton
import android.widget.ImageView


class TemudokterActivity  : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temudokter)
        clicListener();

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            val intent = Intent(this@TemudokterActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }


    }
    public fun clicListener(){

        var imagejantung = findViewById<ImageView>(R.id.btnJantung)



        imagejantung.setOnClickListener {
            jantung()
        }

    }


    public fun jantung(){
        val intent = Intent(this@TemudokterActivity, FasilitasKesehatanActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        startActivity(intent)
    }
}