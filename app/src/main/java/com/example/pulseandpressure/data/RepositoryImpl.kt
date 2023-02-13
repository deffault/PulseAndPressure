package com.example.pulseandpressure.data

import com.example.pulseandpressure.domain.MainData
import com.google.firebase.firestore.FirebaseFirestore
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
                _flow.value = "Запись добавлена"
            }
            .addOnFailureListener {
                val message = it.message
                println("VVVV $message")
                _flow.value = "Ошибка добвления"
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