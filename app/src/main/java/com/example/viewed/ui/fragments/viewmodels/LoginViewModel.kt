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
    fun signIn(
        login: String,
        password: String,
        handlerOk: () -> Unit,
        handlerBad: (error: String) -> Unit
    ) {
        if (login.isEmpty()) {
            handlerBad("Поле email не может быть пустым")
            return
        }
        if (password.isEmpty()) {
            handlerBad("Поле пароля не может быть пустым")
            return
        }
        auth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                handlerOk()
            } else {
                var error = it.exception.toString()
                if (error.contains(Regex("FirebaseAuthInvalidUserException"))) {
                    handlerBad("Неверная пара логин/пароль")
                }
                if (error.contains(Regex("FirebaseAuthInvalidCredentialsException"))) {
                    handlerBad("Неверная пара логин/пароль")
                }
            }
        }
    }
}
