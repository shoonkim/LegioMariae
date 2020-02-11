package com.shoonkim.legiomariae.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.ui.adapter.WAC_Adapter
import com.shoonkim.legiomariae.utils.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.wac_frag_recycler.*
import java.util.*

class WAC_Fragment : RxBaseFragment() {

    private val  wacList by lazy{ wac_rv_list }
    private val fbManager by lazy{FirebaseManager()}
    private val curDate = Date(Calendar.getInstance().timeInMillis)
    private val meetingDay = 1

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
        requestWAC(curDate)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun requestWAC(curDate : Date) {
        val subscription = fbManager.getWACList(curDate, meetingDay)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    requestWAC->(wacList.adapter as WAC_Adapter).addWAC_List(requestWAC)
                },{
                    e->
                }
            )
        subscriptions.add(subscription)
    }
}