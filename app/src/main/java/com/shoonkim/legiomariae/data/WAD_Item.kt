package com.shoonkim.legiomariae.data

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WAD_Item (
    val itemDate : Timestamp,
    val itemLiturgicalDay : String,
    val itemToDayGospel : String?) : Parcelable { }