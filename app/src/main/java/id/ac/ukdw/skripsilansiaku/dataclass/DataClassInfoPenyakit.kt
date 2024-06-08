package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcel
import android.os.Parcelable

class DataClassInfoPenyakit (var dataImagePenyakit:Int,
                             var datajenisPenyakit:String,
                             var dataTitlePenyakit:String,
                             var dataPenjelasanPenyakit:String,
                             var dataDesc: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImagePenyakit)
        parcel.writeString(datajenisPenyakit)
        parcel.writeString(dataTitlePenyakit)
        parcel.writeString(dataPenjelasanPenyakit)
        parcel.writeString(dataDesc)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataClassInfoPenyakit> {
        override fun createFromParcel(parcel: Parcel): DataClassInfoPenyakit {
            return DataClassInfoPenyakit(parcel)
        }

        override fun newArray(size: Int): Array<DataClassInfoPenyakit?> {
            return arrayOfNulls(size)
        }
    }
}