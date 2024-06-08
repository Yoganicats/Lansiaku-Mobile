package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dokterdata (
    val imgDokter: Int,
    val namaDokter: String,
    val jadwalDokter: String,
    val hariDokter:String,
    val waktuDokter:String
) : Parcelable