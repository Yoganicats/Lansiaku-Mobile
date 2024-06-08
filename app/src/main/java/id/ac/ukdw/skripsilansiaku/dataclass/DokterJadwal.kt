package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DokterJadwal(
    val idDokterJadwal: String = "",
    val dataClassDokterKonsultasi: DataClassDokterKonsultasi = DataClassDokterKonsultasi(),
    val dataKonfirmasi: KonfirmasiMdl = KonfirmasiMdl(),
    val jadwal2Mdl: Jadwal2Mdl = Jadwal2Mdl(),
    val batalState: Boolean = false
): Parcelable
