package id.ac.ukdw.skripsilansiaku.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.Dokterdata


class DokterdataAdapter (private val context: Context,
                         var dokter: List<Dokterdata>,
                         val listener: (Dokterdata) -> Unit)
    : RecyclerView.Adapter<DokterdataAdapter.SuperheroViewHolder>() {

    class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgDokter = view.findViewById<ImageView>(R.id.img_item_dokter)
        val namaDokter = view.findViewById<TextView>(R.id.tv_item_name)
        val jadwalDokter = view.findViewById<TextView>(R.id.tv_item_description)
        val hariDokter = view.findViewById<TextView>(R.id.tv_item_jadwal)
        val waktuDokter= view.findViewById<TextView>(R.id.tv_item_jam)

        fun bindView(Dokter: Dokterdata, listener: (Dokterdata) -> Unit) {

            imgDokter.setImageResource(Dokter.imgDokter)
            namaDokter.text = Dokter.namaDokter
            jadwalDokter.text = Dokter.jadwalDokter
            hariDokter.text =Dokter.hariDokter
            waktuDokter.text=Dokter.waktuDokter

            itemView.setOnClickListener {
                listener(Dokter)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pesan, parent, false)
        )

    }


    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bindView(dokter[position], listener)

    }

    override fun getItemCount(): Int = dokter.size

}
