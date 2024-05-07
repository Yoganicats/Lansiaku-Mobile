package id.ac.ukdw.skripsilansiaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi

class DetailDokterKonsultasi : AppCompatActivity() {

    private lateinit var konsulDurasiTxt: TextView
    private lateinit var thnPenglmnKerjaTxt: TextView
    private lateinit var jlhPasienTxt: TextView
    private lateinit var detailImage: ImageView
    private lateinit var txtNamaDokterDetail: TextView
    private lateinit var txtNamaSpesialisDetail: TextView
    private lateinit var keahlianDokter: TextView
    private lateinit var lanjutBtn: Button
    private var oldDataClassDokterKonsultasi: DataClassDokterKonsultasi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dokter_konsultasi)

        if(intent.extras != null){
            val bundle = intent.extras
            oldDataClassDokterKonsultasi = bundle?.getParcelable(INTENT_PARAM)
        }

        konsulDurasiTxt = findViewById(R.id.konsulDurasiTxt)
        thnPenglmnKerjaTxt = findViewById(R.id.tahunPengalamanKerjaTxt)
        jlhPasienTxt = findViewById(R.id.jlhPasienTxt)
        detailImage = findViewById(R.id.detailImage)
        txtNamaDokterDetail = findViewById(R.id.txt_namaDokterDetail)
        txtNamaSpesialisDetail = findViewById(R.id.txt_namaSpesialisDetail)
        lanjutBtn = findViewById(R.id.lanjutBtn)

        keahlianDokter = findViewById(R.id.txtKeahlian)

        detailImage.setImageResource(oldDataClassDokterKonsultasi?.imageDokterKonsultasi ?: -1)
        txtNamaDokterDetail.text = oldDataClassDokterKonsultasi?.namaDokterKonsultasi
        txtNamaSpesialisDetail.text = oldDataClassDokterKonsultasi?.spesialisDokterKonsultasi

        keahlianDokter.text = oldDataClassDokterKonsultasi?.keahlianDokter

        konsulDurasiTxt.text = oldDataClassDokterKonsultasi?.durasiKonsultasi + "\n Durasi Konsultasi"
        thnPenglmnKerjaTxt.text = oldDataClassDokterKonsultasi?.dokterBekerja + "\nPengalaman Kerja"
        jlhPasienTxt.text = oldDataClassDokterKonsultasi?.jumlahPasienKonsultasi + "\n Pasien Konsultasi"

        lanjutBtn.setOnClickListener {
            val intent = Intent(this, Pilih_Jadwal_Konsultasi_2_Activity::class.java)
            intent.putExtra(Pilih_Jadwal_Konsultasi_2_Activity.INTENT_PARAM, oldDataClassDokterKonsultasi)
            startActivity(intent)
        }

    }

    companion object{
        const val INTENT_PARAM = "detaildokterkonsul"
    }
}