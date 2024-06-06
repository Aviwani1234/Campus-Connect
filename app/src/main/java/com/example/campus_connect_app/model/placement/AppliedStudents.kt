package com.example.campus_connect_app.model.placement

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class AppliedStudents(
    val studentId : Int,
    val campusDriveId : Int,
    val fullName  : String?,
    val dob : String?,
    val address : String?,
    val phone : String?,
    val email : String?,
    val gender : String?,
    val course : String?,
    val institute : String?,
    val university : String?,
    val yearOfPassing : String?,
    val percentage : String?,
    val pdf : ByteArray?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createByteArray()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(studentId)
        parcel.writeInt(campusDriveId)
        parcel.writeString(fullName)
        parcel.writeString(dob)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(gender)
        parcel.writeString(course)
        parcel.writeString(institute)
        parcel.writeString(university)
        parcel.writeString(yearOfPassing)
        parcel.writeString(percentage)
        parcel.writeByteArray(pdf)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppliedStudents> {
        override fun createFromParcel(parcel: Parcel): AppliedStudents {
            return AppliedStudents(parcel)
        }

        override fun newArray(size: Int): Array<AppliedStudents?> {
            return arrayOfNulls(size)
        }
    }
}

