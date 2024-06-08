package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassInfoPenyakit

class AdapterInfoPenyakit (
    private val dataPenyakitList: ArrayList<DataClassInfoPenyakit>):
    RecyclerView.Adapter<AdapterInfoPenyakit.ViewHolderClass>() {

    var onItemClick: ((DataClassInfoPenyakit) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_info_penyakit, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataPenyakitList[position]
        holder.rvImagePenyakit.setImageResource(currentItem.dataImagePenyakit)
        holder.rvJenisPenyakit.text = currentItem.datajenisPenyakit
        holder.rvTitlePenyakit.text = currentItem.dataTitlePenyakit
        holder.rvPenjelasanPenyakit.text = currentItem.dataPenjelasanPenyakit


        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataPenyakitList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImagePenyakit: ImageView = itemView.findViewById(R.id.img_penyakit)
        val rvJenisPenyakit: TextView = itemView.findViewById(R.id.txtJenisPenyakit)
        val rvTitlePenyakit: TextView = itemView.findViewById(R.id.txt_Judul_penyakit)
        val rvPenjelasanPenyakit: TextView = itemView.findViewById(R.id.txt_penjelasanPenyakit)

    }
}