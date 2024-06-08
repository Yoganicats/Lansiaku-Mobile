package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DataArtikelClass

class AdapterClassArtikel (private val dataList: ArrayList<DataArtikelClass>): RecyclerView.Adapter<AdapterClassArtikel.ViewHolderClass>() {

    var onItemClick: ((DataArtikelClass) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImageArtikel.setImageResource(currentItem.dataImageArtikel)
        holder.rvTitleArtikel.text = currentItem.dataTitleArtikel
        holder.rvPenjelasanArtikel.text = currentItem.dataPenjelasanArtikel
        holder.rvKategori.text=currentItem.dataKategori

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImageArtikel: ImageView = itemView.findViewById(R.id.img_artikel)
        val rvTitleArtikel: TextView = itemView.findViewById(R.id.tv_judulartikel)
        val rvPenjelasanArtikel: TextView = itemView.findViewById(R.id.tv_penjelasan)
        val rvKategori:TextView =itemView.findViewById(R.id.tv_kategori_artikel)
    }
}