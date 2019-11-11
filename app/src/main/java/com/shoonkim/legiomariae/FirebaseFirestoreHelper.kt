package com.shoonkim.legiomariae

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.util.*
import kotlin.collections.ArrayList

const val TAG:String = "FirebaseFirestoreHelper"

object FirebaseFirestoreHelper{

    private var db : FirebaseFirestore? = null

    fun initFF(){
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db!!.firestoreSettings = settings
    }

    fun isFirebaseFirestore(): Boolean {
        return db != null
    }

    fun getWeekCalender( today : Calendar): ArrayList<WeekCalenderData>{
        val list  = arrayListOf<WeekCalenderData>()

        val curMonth = today.get(Calendar.YEAR).toString() + (today.get(Calendar.MONTH).toInt() + 1).toString()

        val DocumentRef = db!!.collection("Calender")
            .document(curMonth)

        DocumentRef.collection("Day")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list.add(
                        WeekCalenderData(document.id, "",
                        document.getString("LiturgicalDay")!!,
                        document.getString("toDayGospel")!!,
                        "")
                    )
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        if (list != null) {
            for(tmp in list){
                Log.d(TAG, "** ${tmp.day} => ${tmp.liturgicalDay}")
            }
        }

        return list
    }
}
