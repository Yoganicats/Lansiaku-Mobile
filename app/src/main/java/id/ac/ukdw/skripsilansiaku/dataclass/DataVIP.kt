package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataVIP(
    val imgKlinik: Int = 0,
    val nameKlinik: String = "",
    val descKlinik: String = ""
) : Parcelable
