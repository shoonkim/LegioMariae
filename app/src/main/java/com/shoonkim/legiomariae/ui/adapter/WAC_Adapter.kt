package com.shoonkim.legiomariae.ui.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.shoonkim.legiomariae.data.WAC_Item

class WAC_Adapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items : ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ItemAdapter>()

    private val loadingItem = object : ViewType{
        override fun getViewType() = AdapterType.LOADING
    }

    init {
        delegateAdapters.put(AdapterType.LOADING, LoadingItemAdapter())
        delegateAdapters.put(AdapterType.WACITEM, WAC_ItemAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addWAC_List(wacList : List<WAC_Item>){
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(wacList.reversed())
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size+1)
    }

    private fun getLastPosition() = if(items.lastIndex == -1) 0 else items.lastIndex

}