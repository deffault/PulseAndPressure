package com.example.pulseandpressure.data

import com.example.pulseandpressure.domain.MainData
import com.example.pulseandpressure.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : Repository() {

    override fun insert(data: MainData) {
        val dataMap = map(data = data)

        db.collection(COLLECTION_NAME)
            .add(dataMap)
            .addOnSuccessListener {
                _addDataFlow.value = "Запись добавлена"
            }
            .addOnFailureListener {
                val message = it.message
                println("VVVV $message")
                _addDataFlow.value = "Ошибка добвления"
            }
    }

    override fun getDataList() {
        _listDataFlow.value = Resource.Loading
        db.collection(COLLECTION_NAME)
            .orderBy("created_at", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { queryShapshot ->
                val dataList = mutableListOf<MainData>()
                queryShapshot.forEach { documentSnapshot ->
                    val item = MainData(
                        id = documentSnapshot.id,
                        time = documentSnapshot.get("time") as String,
                        topPressure = documentSnapshot.get("top_pressure") as Long,
                        bottomPressure = documentSnapshot.get("bottom_pressure") as Long,
                        pulse = documentSnapshot.get("pulse") as Long,
                        date = documentSnapshot.get("date") as String
                    )

                    dataList.add(item)
                }

                _listDataFlow.value = Resource.Success(data = dataList)
            }
            .addOnFailureListener {
                _listDataFlow.value = Resource.Error(message = "Ошибка загрузки данных")
            }
    }

    private fun map(data: MainData): Map<String, Any> {
        return hashMapOf(
            "time" to data.time,
            "top_pressure" to data.topPressure,
            "bottom_pressure" to data.bottomPressure,
            "pulse" to data.pulse,
            "date" to data.date,
            "created_at" to Date()
        )
    }

    companion object {
        private const val COLLECTION_NAME = "/data"
    }
}