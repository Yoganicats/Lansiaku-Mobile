package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcel
import android.os.Parcelable

data class DataArtikelClass(var dataImageArtikel:Int,
                            var dataTitleArtikel:String,
                            var dataPenjelasanArtikel:String,
                            var dataKategori: String,
                            var dataDesc: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,

//        parcel.readString()!!,
//        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImageArtikel)
        parcel.writeString(dataTitleArtikel)
        parcel.writeString(dataPenjelasanArtikel)
        parcel.writeString(dataKategori)
        parcel.writeString(dataDesc)

//        parcel.writeString(dataDescArtikel)
//        parcel.writeInt(dataDetailImageArtikel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataArtikelClass> {
        override fun createFromParcel(parcel: Parcel):DataArtikelClass {
            return DataArtikelClass(parcel)
        }

        override fun newArray(size: Int): Array<DataArtikelClass?> {
            return arrayOfNulls(size)
        }
    }
}