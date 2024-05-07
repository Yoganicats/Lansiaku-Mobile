package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

/*@Parcelize
data class JadwalMdl(
    val hari: String = "",
    val jam: String = "",
    val waktu: String = "",
    val metodePembyrn: String = "",
    val totalByr: String = ""
): Parcelable*/

@Parcelize
data class Jadwal2Mdl(
    val year: Int = 0,
    val month: Int = 0,
    val date: Int = 0,
    val hours: Int = 0,
    val minutes: Int = 0,
    val metodePembyrn: String = "",
    val totalByr: String = ""
): Parcelable