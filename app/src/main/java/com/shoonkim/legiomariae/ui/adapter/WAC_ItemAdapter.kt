package com.shoonkim.legiomariae.ui.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.data.WAC_Item
import com.shoonkim.legiomariae.utils.inflate
import com.shoonkim.legiomariae.utils.numTodayWeek
import kotlinx.android.synthetic.main.wac_item_day.view.*
import java.util.*

class WAC_ItemAdapter : ItemAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return WAC_ViewHonder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder as WAC_ViewHonder
        holder.bind(item as WAC_Item)
    }

    inner class WAC_ViewHonder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.wac_item_day)){

        private val txt_day = itemView.wci_cardwiew_txt_day
        private val txt_liturgical_day = itemView.wci_cardwiew_txt_liturgical_day
        private val txt_day_week = itemView.wci_cardwiew_txt_day_week
        private val txt_to_day_gospel = itemView.wci_cardwiew_txt_to_day_gospel
        private val txt_content = itemView.wci_cardwiew_txt_content

        fun bind(item : WAC_Item){
            val cal = Calendar.getInstance()
            cal.time = Date(item.itemDate)
            val dayWeek = cal.get(Calendar.DAY_OF_WEEK)
            txt_day.text = cal.get(Calendar.DAY_OF_MONTH).toString()
            txt_day_week.text = numTodayWeek(dayWeek)

            if(dayWeek == 1){
                txt_day.setTextColor(Color.parseColor("#CC3D3D"))
                txt_day_week.setTextColor(Color.parseColor("#CC3D3D"))
            }

            txt_liturgical_day.text = item.itemLiturgicalDay
            txt_to_day_gospel.text = item.itemToDayGospel
            txt_content.text = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        }

    }
}