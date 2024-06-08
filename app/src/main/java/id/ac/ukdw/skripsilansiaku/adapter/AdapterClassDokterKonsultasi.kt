package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassDokterKonsultasi

class AdapterClassDokterKonsultasi (private val DokterKonsultasiList: ArrayList<DataClassDokterKonsultasi>): RecyclerView.Adapter<AdapterClassDokterKonsultasi.ViewHolderClass>() {

    var onItemClick: ((DataClassDokterKonsultasi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pilih_dokter_konsultasi, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val dokterKonsultasi = DokterKonsultasiList[position]
        holder.imageViewDokterKonsultasi.setImageResource(dokterKonsultasi.imageDokterKonsultasi)
        holder.txtNamadokterKonsultasi.text = dokterKonsultasi.namaDokterKonsultasi
        holder.txtSpesialisDokterKonsultasi.text = dokterKonsultasi.spesialisDokterKonsultasi
        holder.txtHargaKonsultasi.text = dokterKonsultasi.hargaDokterKonsultasi
        holder.txtDokterBekerja.text = dokterKonsultasi.dokterBekerja
        holder.txtMulaiKonsultasi.text = dokterKonsultasi.txtMulaiKonsul

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(dokterKonsultasi)
        }
    }

    override fun getItemCount(): Int {
        return DokterKonsultasiList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageViewDokterKonsultasi : ImageView = itemView.findViewById(R.id.img_dokter_konsultasi)
        val txtNamadokterKonsultasi : TextView = itemView.findViewById(R.id.tv_namaDokterKonsultasi)
        val txtSpesialisDokterKonsultasi : TextView = itemView.findViewById(R.id.tv_spesialisKonsultasi)
        val txtHargaKonsultasi : TextView = itemView.findViewById(R.id.tv_hargaDokterKonsultasi)
        val txtDokterBekerja : TextView = itemView.findViewById(R.id.tv_lamakerjaDokter)
        val txtMulaiKonsultasi : TextView = itemView.findViewById(R.id.tv_MulaiKonsultasi)
    }
}