package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.RumahSakitDataClass


class  RumahSakitAdapter (private val dataList: ArrayList<RumahSakitDataClass>): RecyclerView.Adapter<RumahSakitAdapter.ViewHolderClass>() {

    var onItemClick: ((RumahSakitDataClass) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rumah_sakit, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle
        holder.rvAlamat.text = currentItem.dataAlamat
        holder.rvJadwal.text = currentItem.dataJadwal
        holder.rvRating.text =currentItem.dataRating

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.title)
        val rvAlamat: TextView = itemView.findViewById(R.id.tv_alamat)
        val rvJadwal: TextView = itemView.findViewById(R.id.tv_item_jadwal)
        val rvRating: TextView = itemView.findViewById(R.id.tv_rating)
    }
}