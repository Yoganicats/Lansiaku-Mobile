package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DokterJadwal
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class RiwayatAdapter (private val dataList: ArrayList<DokterJadwal>): RecyclerView.Adapter<RiwayatAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]

        if(!currentItem.batalState){
            holder.stateActiveImg.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, R.drawable.bulet_ijo))
        }else{
            holder.stateActiveImg.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, R.drawable.bulet_merah))
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
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val stateActiveImg = itemView.findViewById<ImageView>(R.id.stateActiveImg)
        val imgItemDokter = itemView.findViewById<ImageView>(R.id.img_item_dokter)
        val tvItemKategori = itemView.findViewById<TextView>(R.id.tv_item_kategori)
        val tvItemDeskripsi = itemView.findViewById<TextView>(R.id.tv_item_description)
        val tvItemJadwal = itemView.findViewById<TextView>(R.id.tv_item_jadwal)
        val tvItemJam = itemView.findViewById<TextView>(R.id.tv_item_jam)
    }
}