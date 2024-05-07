package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NamaDokterData(
    val imageDokter: Int = 0,
    val namaDokter: String = "",
    val namaSpesialis: String = "",
    val hargaDokter: String = ""
) : Parcelable
