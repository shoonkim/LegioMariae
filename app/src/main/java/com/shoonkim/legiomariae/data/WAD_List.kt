package com.shoonkim.legiomariae.data
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WAD_List(
    var page : Int?,
    val results : List<WAD_Item>) : Parcelable { }