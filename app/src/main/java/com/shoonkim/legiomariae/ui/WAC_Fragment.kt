package com.shoonkim.legiomariae.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.utils.inflate
import kotlinx.android.synthetic.main.wac_frag_recycler.*

class WAC_Fragment : Fragment() {

    private val  wacList by lazy{ wca_rv_news_list }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.wac_frag_recycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        wacList.setHasFixedSize(true)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            wacList.layoutManager = LinearLayoutManager(context)
        }
    }
}