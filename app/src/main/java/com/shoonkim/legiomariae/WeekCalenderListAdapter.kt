package com.shoonkim.legiomariae

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.data.WCItem

class WeekCalenderListAdapter(val context: Context, val list:ArrayList<WCItem>) :
    RecyclerView.Adapter<WeekCalenderListAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        val day = itemView?.findViewById<TextView>(R.id.wci_cardwiew_txt_day)
        val day_week = itemView?.findViewById<TextView>(R.id.wci_cardwiew_txt_day_week)
        val liturgical_day = itemView?.findViewById<TextView>(R.id.wci_cardwiew_txt_liturgical_day)
        val to_day_gospel = itemView?.findViewById<TextView>(R.id.wci_cardwiew_txt_to_day_gospel)
        val content = itemView?.findViewById<TextView>(R.id.wci_cardwiew_txt_content)

        fun bind(data: WCItem, context: Context){

            day?.text = data.day
            day_week?.text = data.dayWeek
            liturgical_day?.text = data.liturgicalDay
            to_day_gospel?.text = data.toDayGospel
            content?.text = data.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
        val view = LayoutInflater.from(context).inflate(R.layout.week_calender_item, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(list[position], context)
    }

    fun clearAndAddList(addList:ArrayList<WCItem>){
        list.clear()
        notifyItemRangeRemoved(0, itemCount)

        list.addAll(addList)
        notifyItemRangeInserted(0, addList.size)
    }
}