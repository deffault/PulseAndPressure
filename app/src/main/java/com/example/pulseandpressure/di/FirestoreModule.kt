package com.example.pulseandpressure.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirestoreModule {

    @Provides
    @Singleton
    fun provideFirebaseDB(): FirebaseFirestore {
        return Firebase.firestore
    }
}