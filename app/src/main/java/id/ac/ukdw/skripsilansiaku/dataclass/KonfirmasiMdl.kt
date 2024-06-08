package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcelable
import com.google.firebase.auth.FirebaseUser
import kotlinx.parcelize.Parcelize

@Parcelize
data class KonfirmasiMdl(
    val rsData: RumahSakitDataClass = RumahSakitDataClass(),
    val jnsData: DataVIP = DataVIP(),
    val dokterData: NamaDokterData = NamaDokterData(),
    val pasienData: String = "",
    val metodePembayaran: String = "",
    val totalBiaya: Int = 0
): Parcelable