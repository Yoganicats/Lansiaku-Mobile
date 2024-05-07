package id.ac.ukdw.skripsilansiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.KonsultasiActivity
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.TemudokterActivity
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassSpesialis

class AdapterTemuDokterSpesialis constructor(
    private val getActivity: TemudokterActivity,
    private val spesialisList: List<DataClassSpesialis>) :
    RecyclerView.Adapter<AdapterTemuDokterSpesialis.MyViewHolder>() {

    var onItemClick: ((DataClassSpesialis) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spesialis, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentSpesialis = spesialisList[position]

        holder.tvSpesialisTitle.text = spesialisList[position].title
        holder.ivSpesialisImg.setImageResource(spesialisList[position].image)

        holder.cardView.setOnClickListener {
            onItemClick?.invoke(currentSpesialis)
        }
    }

    override fun getItemCount(): Int {
        return spesialisList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpesialisTitle: TextView = itemView.findViewById(R.id.tvSpesialisTitle)
        val ivSpesialisImg: ImageView = itemView.findViewById(R.id.ivSpesialisImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

}