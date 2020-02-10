package com.shoonkim.legiomariae.ui

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.shoonkim.legiomariae.data.WAC_Item
import io.reactivex.Observable
import java.util.*

class FirebaseManager {

    @SuppressLint("LongLogTag")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getWACList(curDate : Date) : Observable<List<WAC_Item>>{

        return Observable.create { subscriber ->
            val wacList = mutableListOf<WAC_Item>()

            val db = FirebaseFirestore.getInstance()
            val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
            db.firestoreSettings = settings

            var isRun : Boolean = true

            val DocumentRef = db.collection("Calender")

            DocumentRef.whereGreaterThanOrEqualTo("date", Timestamp(curDate))
                .orderBy("date")
                .limit(14)
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
                        Log.d("FirebaseFirestoreManager", "${document.id} => ${document.data}")
                    }
                    isRun = false
                }
                .addOnFailureListener{
                    exception ->
                    Log.d("FirebaseFirestoreManager", "Failure")
                    isRun = false
                }

            while(isRun){ }

            subscriber.onNext(wacList)
            subscriber.onComplete()
        }
    }
}