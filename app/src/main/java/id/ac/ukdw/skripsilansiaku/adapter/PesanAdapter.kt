package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class PesanAdapter (private val dataList: ArrayList<DokterJadwal>): RecyclerView.Adapter<PesanAdapter.ViewHolderClass>() {

    var onItemClick: ((DokterJadwal) -> Unit)? = null
    var onBatalClick: ((DokterJadwal) -> Unit)? = null
    var onAturClick: ((DokterJadwal) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pesan, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]

        if(!currentItem.batalState){
            holder.btnBatalkanJanji.visibility = View.VISIBLE
            holder.btnAturUlangJadwal.visibility = View.VISIBLE
        }else{
            holder.btnBatalkanJanji.visibility = View.GONE
            holder.btnAturUlangJadwal.visibility = View.GONE
        }

        // harus aware dengan dataclassdokter yang kosong dengan datakonf dokter data yang kosong
        if(currentItem.dataClassDokterKonsultasi.namaDokterKonsultasi != ""){
            // konsultasi
            holder.tvItemKategori.text = "KONSULTASI"
            holder.tvItemName.text = currentItem.dataClassDokterKonsultasi.namaDokterKonsultasi
            holder.imgItemDokter.setImageResource(currentItem.dataClassDokterKonsultasi.imageDokterKonsultasi)
            holder.tvItemDeskripsi.text = currentItem.dataClassDokterKonsultasi.spesialisDokterKonsultasi

        }else{
            // temu dokter
            holder.tvItemKategori.text = "TEMU - DOKTER"
            holder.tvItemName.text = currentItem.dataKonfirmasi.dokterData.namaDokter
            holder.imgItemDokter.setImageResource(currentItem.dataKonfirmasi.dokterData.imageDokter)
            holder.tvItemDeskripsi.text = currentItem.dataKonfirmasi.dokterData.namaSpesialis
        }

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, currentItem.jadwal2Mdl.year)
        calendar.set(Calendar.MONTH, currentItem.jadwal2Mdl.month - 1)
        calendar.set(Calendar.DAY_OF_MONTH, currentItem.jadwal2Mdl.date)
        calendar.set(Calendar.HOUR_OF_DAY, currentItem.jadwal2Mdl.hours)
        calendar.set(Calendar.MINUTE, currentItem.jadwal2Mdl.minutes)
        calendar.set(Calendar.SECOND, 0)

        val dateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID"))
        val currentDate = dateFormat.format(calendar.time)

        holder.tvItemJadwal.text = currentDate
        holder.tvItemJam.text = LocalTime.of(currentItem.jadwal2Mdl.hours, currentItem.jadwal2Mdl.minutes).format(
            DateTimeFormatter.ofPattern("HH:mm"))

        holder.btnBatalkanJanji.setOnClickListener {
            onBatalClick?.invoke(currentItem)
        }

        holder.btnAturUlangJadwal.setOnClickListener {
            onAturClick?.invoke(currentItem)
        }

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnBatalkanJanji = itemView.findViewById<Button>(R.id.btn_batalkan_janji)
        val btnAturUlangJadwal = itemView.findViewById<Button>(R.id.btn_Atur_ulang_jadwal)
        val tvItemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val imgItemDokter = itemView.findViewById<ImageView>(R.id.img_item_dokter)
        val tvItemKategori = itemView.findViewById<TextView>(R.id.tv_item_kategori)
        val tvItemDeskripsi = itemView.findViewById<TextView>(R.id.tv_item_description)
        val tvItemJadwal = itemView.findViewById<TextView>(R.id.tv_item_jadwal)
        val tvItemJam = itemView.findViewById<TextView>(R.id.tv_item_jam)
    }
}