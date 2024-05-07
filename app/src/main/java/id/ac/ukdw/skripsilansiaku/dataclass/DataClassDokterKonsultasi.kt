package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataClassDokterKonsultasi(
    var imageDokterKonsultasi: Int = 0,
    var namaDokterKonsultasi: String = "",
    var spesialisDokterKonsultasi: String = "",
    var hargaDokterKonsultasi: String = "",
    var dokterBekerja: String = "",
    var txtMulaiKonsul: String = "",
    var durasiKonsultasi: String = "",
    var jumlahPasienKonsultasi: String = "",
    var keahlianDokter: String = ""
) : Parcelable

