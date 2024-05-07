package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R

import id.ac.ukdw.skripsilansiaku.dataclass.NamaDokterData

class PilihDokterAdapter (private val dokterList: ArrayList<NamaDokterData>)
    : RecyclerView.Adapter<PilihDokterAdapter.FoodViewHolder>(){

    var onItemClick : ((NamaDokterData) -> Unit)? = null
    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.img_item_dokter)
        val itemDokter : TextView = itemView.findViewById(R.id.tv_namaDokter)
        val itemSpesialis : TextView = itemView.findViewById(R.id.tv_spesialis)
        val itemHarga : TextView = itemView.findViewById(R.id.tv_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pilih_dokter, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val dokter = dokterList[position]
        holder.imageView.setImageResource(dokter.imageDokter)
        holder.itemDokter.text = dokter.namaDokter
        holder.itemSpesialis.text = dokter.namaSpesialis
        holder.itemHarga.text = dokter.hargaDokter

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(dokter)
        }
    }

    override fun getItemCount(): Int {
        return dokterList.size
    }
}