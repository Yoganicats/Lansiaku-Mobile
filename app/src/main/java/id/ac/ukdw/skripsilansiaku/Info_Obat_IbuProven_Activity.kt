package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Info_Obat_IbuProven_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_obat_ibu_proven)

        val btnback = findViewById<ImageButton>(R.id.backbtn)
        btnback.setOnClickListener {
            startActivity(
                Intent(this@Info_Obat_IbuProven_Activity, Info_ObatActivity::class.java)
            )
        }
    }
}