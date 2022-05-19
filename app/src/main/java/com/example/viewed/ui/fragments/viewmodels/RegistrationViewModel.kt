package com.example.viewed.ui.fragments.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth
) : ViewModel() {
    private var auth = firebaseAuth
    fun signUp(login: String, password: String, handlerOk: () -> Unit, handlerBad: (error: String) -> Unit) {
        Log.d(TAG, login)
        Log.d(TAG, password)
        auth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    print(user)
                    handlerOk()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                    handlerBad(it.exception.toString())
                }
            }
    }
}
