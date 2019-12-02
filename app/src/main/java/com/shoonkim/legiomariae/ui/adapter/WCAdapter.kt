package com.shoonkim.legiomariae.ui.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.data.WCItem
import java.util.*

class WCAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: ArrayList<ViewType>

    private var delegateAdapters = SparseArrayCompat<ItemAdapter>()

    private val loadingItem = object : ViewType{
        override fun getViewType() = AdapterType.LOADING_WC
    }

    init {
        delegateAdapters.put(AdapterType.LOADING_WC, LoadingWCItemAdapter())
        delegateAdapters.put(AdapterType.WC, WCItemAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addWCList(wcList: List<WCItem>) {

        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(wcList)
        //items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size+1)
    }

}