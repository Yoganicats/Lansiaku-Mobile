package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RumahSakitDataClass(
    var dataImage: Int = 0,
    var dataTitle: String = "",
    var dataAlamat: String = "",
    var dataJadwal: String = "",
    var dataRating: String = ""
) : Parcelable