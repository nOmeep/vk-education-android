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
    fun signUp(
        login: String,
        password: String,
        passwordRepeat: String,
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
        if (passwordRepeat.isEmpty()) {
            handlerBad("Поле повтора пароля не может быть пустым")
            return
        }
        if (password != passwordRepeat) {
            handlerBad("Поля пароля и повтора пороля не совпадают")
            return
        }
        auth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    handlerOk()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                    var error = it.exception.toString()
                    if (error.contains(Regex("FirebaseAuthWeakPasswordException"))) {
                        handlerBad("Пароль не является достаточно сложным")
                    }
                    if (error.contains(Regex("FirebaseAuthInvalidCredentialsException"))) {
                        handlerBad("Email адрес имеет неверный формат")
                    }
                    if (error.contains(Regex("FirebaseAuthUserCollisionException"))) {
                        handlerBad("Учетная запись с таким email уже существует")
                    }
                }
            }
    }
}
