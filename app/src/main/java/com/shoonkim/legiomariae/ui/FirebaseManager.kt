package com.shoonkim.legiomariae.ui

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.shoonkim.legiomariae.data.WAC_Item
import com.shoonkim.legiomariae.utils.dateTodayWeek
import com.shoonkim.legiomariae.utils.fewDaysAgo
import io.reactivex.Observable
import java.util.*

class FirebaseManager {

    @SuppressLint("LongLogTag")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getWACList(curDate : Date, meetingDay : Int) : Observable<List<WAC_Item>>{

        val searchDays = 7 + dateTodayWeek(curDate) - meetingDay
        val searchStartDay = fewDaysAgo(curDate,  searchDays)

        return Observable.create { subscriber ->
            val wacList = mutableListOf<WAC_Item>()

            val db = FirebaseFirestore.getInstance()
            val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
            db.firestoreSettings = settings

            val DocumentRef = db.collection("Calender")

            DocumentRef.whereGreaterThanOrEqualTo("date", Timestamp(searchStartDay.time))
                .orderBy("date")
                .limit(searchDays.toLong())
                .get()
                .addOnSuccessListener {
                    result->
                    for (document in result){
                        wacList.add(
                            WAC_Item(
                                document.getTimestamp("date")!!.toDate().time,
                                document.getString("liturgicalDay")!!,
                                document.getString("toDayGospel")!!
                            )
                        )
                    }
                    subscriber.onNext(wacList)
                    subscriber.onComplete()
                }
                .addOnFailureListener{
                    exception ->
                    subscriber.onError(Throwable(exception.message))
                }
        }
    }
}