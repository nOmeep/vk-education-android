package com.example.viewed.ui.fragments.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth
) : ViewModel() {
    private var auth = firebaseAuth
    fun signIn(login: String, password: String, handlerOk: () -> Unit, handlerBad: (error: String) -> Unit) {
        auth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                handlerOk()
            } else {
                Log.w(TAG, "signInWithEmail:failure", it.exception)
                handlerBad(it.exception.toString())
                auth.signOut()
            }
        }
    }
}
