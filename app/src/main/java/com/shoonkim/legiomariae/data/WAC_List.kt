package com.shoonkim.legiomariae.data
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WAC_List(
    var page : Int?,
    val results : List<WAC_Item>) : Parcelable { }