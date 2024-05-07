package id.ac.ukdw.skripsilansiaku.dataclass

import android.os.Parcel
import android.os.Parcelable

class DataObatClass(var dataImageObat:Int,
                    var dataJenisObat:String,
                    var dataTitleObat:String,
                    var dataPenjelasanObat:String,
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
        parcel.writeInt(dataImageObat)
        parcel.writeString(dataJenisObat)
        parcel.writeString(dataTitleObat)
        parcel.writeString(dataPenjelasanObat)
        parcel.writeString(dataDesc)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataObatClass> {
        override fun createFromParcel(parcel: Parcel): DataObatClass {
            return DataObatClass(parcel)
        }

        override fun newArray(size: Int): Array<DataObatClass?> {
            return arrayOfNulls(size)
        }
    }
}