package id.ac.ukdw.skripsilansiaku.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ukdw.skripsilansiaku.KonsultasiActivity
import id.ac.ukdw.skripsilansiaku.R
import id.ac.ukdw.skripsilansiaku.TemudokterActivity
import id.ac.ukdw.skripsilansiaku.dataclass.DataClassSpesialis

internal class AdapterTemuDokterSpesialis(
    // on below line we are creating two
    // variables for course list and context
    private val courseList: List<DataClassSpesialis>,
    private val context: Context
) :
    BaseAdapter() {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseTV: TextView
    private lateinit var courseIV: ImageView

    // below method is use to return the count of course list
    override fun getCount(): Int {
        return courseList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.item_spesialis, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        courseIV = convertView!!.findViewById(R.id.ivSpesialisImg)
        courseTV = convertView!!.findViewById(R.id.tvSpesialisTitle)
        // on below line we are setting image for our course image view.
        courseIV.setImageResource(courseList.get(position).image)
        // on below line we are setting text in our course text view.
        courseTV.setText(courseList.get(position).title)
        // at last we are returning our convert view.
        return convertView
    }
}