package id.ac.ukdw.skripsilansiaku.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.dataclass.DataVIP


class DataKlinikAdapter (private val context: Context,
                         private val klinik: List<DataVIP>,
                         val listener: (DataVIP) -> Unit)
    : RecyclerView.Adapter<DataKlinikAdapter.SuperheroViewHolder>() {

    class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgSuperhero = view.findViewById<ImageView>(R.id.imageItem)
        val nameSuperhero = view.findViewById<TextView>(R.id.tv_klinik)
        val descSuperhero = view.findViewById<TextView>(R.id.tv_antrian)

        fun bindView(klinik: DataVIP, listener: (DataVIP) -> Unit) {

            imgSuperhero.setImageResource(klinik.imgKlinik)
            nameSuperhero.text = klinik.nameKlinik
            descSuperhero.text = klinik.descKlinik
            itemView.setOnClickListener {
                listener(klinik)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_fasilitas_kesehatan, parent, false)
        )

    }


    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bindView(klinik[position], listener)

    }

    override fun getItemCount(): Int = klinik.size

}