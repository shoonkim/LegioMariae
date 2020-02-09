package com.shoonkim.legiomariae.ui

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.data.WAC_Item
import com.shoonkim.legiomariae.ui.adapter.WAC_Adapter
import com.shoonkim.legiomariae.utils.inflate
import kotlinx.android.synthetic.main.wac_frag_recycler.*

class WAC_Fragment : Fragment() {

    private val  wacList by lazy{ wac_rv_list }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.wac_frag_recycler)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wacList.setHasFixedSize(true)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            wacList.layoutManager = LinearLayoutManager(context)
        }

        if(wac_rv_list.adapter == null ){
            wac_rv_list.adapter = WAC_Adapter()
        }

        if(savedInstanceState == null){
            val wacList = mutableListOf<WAC_Item>()

            for(i in 1..10){
                wacList.add(
                    WAC_Item(
                        Calendar.getInstance().timeInMillis,
                        "AA",
                        "BB"
                    )
                )
            }

            (wac_rv_list.adapter as WAC_Adapter).addWAC_List(wacList)
        }

    }
}