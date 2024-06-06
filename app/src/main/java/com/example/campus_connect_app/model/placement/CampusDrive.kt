package com.example.campus_connect_app.model.placement

import android.os.Parcel
import android.os.Parcelable

data class CampusDrive(val driveId : Int = 0,
                       val role : String,
                       val companyName: String,
                       val location: String,
                       val companyDescription: String,
                       val fromDate: String,
                       val toDate: String,
                       val jobType: String,
                       val hiringCount: String,
                       val requirements: String,
                       val requirementsEdu: String,
                       val salary: String,
                       val experience: String,
                       val rounds: String,
                       ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(driveId)
        parcel.writeString(role)
        parcel.writeString(companyName)
        parcel.writeString(location)
        parcel.writeString(companyDescription)
        parcel.writeString(fromDate)
        parcel.writeString(toDate)
        parcel.writeString(jobType)
        parcel.writeString(hiringCount)
        parcel.writeString(requirements)
        parcel.writeString(requirementsEdu)
        parcel.writeString(salary)
        parcel.writeString(experience)
        parcel.writeString(rounds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CampusDrive> {
        override fun createFromParcel(parcel: Parcel): CampusDrive {
            return CampusDrive(parcel)
        }

        override fun newArray(size: Int): Array<CampusDrive?> {
            return arrayOfNulls(size)
        }
    }
}

