package com.shoonkim.legiomariae.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.data.WCItem
import com.shoonkim.legiomariae.ui.adapter.WCAdapter
import com.shoonkim.legiomariae.ui.adapter.WCItemAdapter
import com.shoonkim.legiomariae.utils.inflate
import kotlinx.android.synthetic.main.wac_frag_recycler.*

class WCAFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.wac_frag_recycler, container, false)
        return container?.inflate(R.layout.wac_frag_recycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wca_calender_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        if(wca_calender_list.adapter == null){
            wca_calender_list.adapter = WCAdapter()
        }

        if(savedInstanceState == null){
            val wcList = mutableListOf<WCItem>()
            wcList.add(
                WCItem("1",
                    "TUE",
                    "(백)아기 예수의 성녀 데레사 동정 학자 기념일",
                    "예수님께서는 예루살렘으로 가시려고 마음을 굳히셨다.",
                    "테스트")
            )
            (wca_calender_list.adapter as WCAdapter).addWCList(wcList)
        }
    }
}