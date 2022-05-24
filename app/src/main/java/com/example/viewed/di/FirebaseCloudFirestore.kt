package com.example.viewed.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseCloudFirestore {
    @Provides
    @Singleton
    fun provideFirebaseCloudFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}
