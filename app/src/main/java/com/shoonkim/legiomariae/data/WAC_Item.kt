package com.shoonkim.legiomariae.data

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.shoonkim.legiomariae.ui.adapter.AdapterType
import com.shoonkim.legiomariae.ui.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WAC_Item (
    val itemDate : Long,
    val itemLiturgicalDay : String,
    val itemToDayGospel : String?) : ViewType, Parcelable {
    override fun getViewType() = AdapterType.WACITEM
}