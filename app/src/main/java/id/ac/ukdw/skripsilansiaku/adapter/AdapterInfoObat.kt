package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DataObatClass

class AdapterInfoObat (private val dataListObat: ArrayList<DataObatClass>): RecyclerView.Adapter<AdapterInfoObat.ViewHolderClass>() {

    var onItemClick: ((DataObatClass) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_info_obat, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataListObat[position]
        holder.rvImageObat.setImageResource(currentItem.dataImageObat)
        holder.rvJenisObat.text = currentItem.dataJenisObat
        holder.rvTitleObat.text = currentItem.dataTitleObat
        holder.rvPenjelasanObat.text = currentItem.dataPenjelasanObat

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataListObat.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImageObat: ImageView = itemView.findViewById(R.id.image_obat)
        val rvJenisObat: TextView = itemView.findViewById(R.id.txtJenisObat)
        val rvTitleObat: TextView = itemView.findViewById(R.id.txt_JudulObat)
        val rvPenjelasanObat: TextView = itemView.findViewById(R.id.txt_penjelasanObat)

    }
}