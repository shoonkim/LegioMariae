package com.shoonkim.legiomariae.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.utils.inflate

class WCItemLoadingAdapter : ItemAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.wac_calender_item_loading))

}