package com.shoonkim.legiomariae.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.data.WCItem
import com.shoonkim.legiomariae.utils.inflate
import kotlinx.android.synthetic.main.week_calender_item.view.*

class WCItemAdapter : ItemAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return WCViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

        holder as WCViewHolder
        holder.bind(item as WCItem)
    }

    inner class WCViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.week_calender_item)) {
        private val day = itemView.wci_cardwiew_txt_day
        private val dayWeek = itemView.wci_cardwiew_txt_day_week
        private val liturgicalDay = itemView.wci_cardwiew_txt_liturgical_day
        private val toDayGospel = itemView.wci_cardwiew_txt_to_day_gospel
        private val content = itemView.wci_cardwiew_txt_content

        fun bind(item: WCItem){
            day.text = item.day
            dayWeek.text = item.dayWeek
            liturgicalDay.text = item.liturgicalDay
            toDayGospel.text = item.toDayGospel
            content.text = item.content
        }
    }
}