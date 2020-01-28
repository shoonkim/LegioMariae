package com.shoonkim.legiomariae.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.data.WAD_Item
import com.shoonkim.legiomariae.utils.inflate
import kotlinx.android.synthetic.main.wac_item_day.view.*

class WAC_ItemAdapter : ItemAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return WAC_ViewHonder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder as WAC_ViewHonder
        holder.bind(item as WAD_Item)
    }

    inner class WAC_ViewHonder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.wac_item_day)){

        private val txt_day = itemView.wci_cardwiew_txt_day
        private val txt_liturgical_day = itemView.wci_cardwiew_txt_liturgical_day
        private val txt_day_week = itemView.wci_cardwiew_txt_day_week
        private val txt_to_day_gospel = itemView.wci_cardwiew_txt_to_day_gospel
        private val txt_content = itemView.wci_cardwiew_txt_content

        fun bind(item : WAD_Item){
            //val c =
            // 여기부터 코딩....^^;

        }

    }
}