package com.shoonkim.legiomariae.ui

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.shoonkim.legiomariae.data.WCItem
import io.reactivex.Observable

class FirebaseFirestoreManager {

    @SuppressLint("LongLogTag")
    fun getWeekCalenderList(): Observable<List<WCItem>> {

        return Observable.create{subscriber->
            val wcList = mutableListOf<WCItem>()

            val db = FirebaseFirestore.getInstance()
            val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
            db.firestoreSettings = settings

            var isRun : Boolean = true

            val DocumentRef = db.collection("Calender")
                .document("201911")

            DocumentRef.collection("Day")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        wcList.add(
                            WCItem(
                                document.id, "",
                                document.getString("LiturgicalDay")!!,
                                document.getString("toDayGospel")!!,
                                ""
                            )
                        )
                        Log.d("FirebaseFirestoreManager", "${document.id} => ${document.data}")
                    }

                    isRun = false
                }
                .addOnFailureListener { exception ->
                    isRun = false
                }

            while(isRun){ }

            subscriber.onNext(wcList)
            subscriber.onComplete()
        }
    }
}