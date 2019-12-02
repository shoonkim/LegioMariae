package com.shoonkim.legiomariae.data

import android.os.Parcelable
import com.shoonkim.legiomariae.ui.adapter.AdapterType
import com.shoonkim.legiomariae.ui.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WCItem (
    val day: String,
    val dayWeek: String,
    val liturgicalDay: String,
    val toDayGospel: String,
    val content: String?): ViewType, Parcelable{
    override fun getViewType() = AdapterType.WC
}

@Parcelize
data class WCList(
    var page: Int?,
    val results: List<WCItem>): Parcelable{}
