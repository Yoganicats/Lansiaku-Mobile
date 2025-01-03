package id.ac.ukdw.skripsilansiaku.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import id.ac.ukdw.skripsilansiaku.R

import java.util.*

class ViewPagerAdapter(val context: Context, val imageList: List<Int>) : PagerAdapter() {

    // sebagai get count untuk mengembalikan ukuran gambar
    override fun getCount(): Int {
        return imageList.size
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }


    // menggembungkan file tata letak
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // on below line we are initializing
        // our layout inflater.
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = mLayoutInflater.inflate(R.layout.image_slider_item, container, false)

        // insisasi id
        val imageView: ImageView = itemView.findViewById<View>(R.id.idIVImage) as ImageView

        imageView.setImageResource(imageList.get(position))


        Objects.requireNonNull(container).addView(itemView)


        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as RelativeLayout)
    }
}
