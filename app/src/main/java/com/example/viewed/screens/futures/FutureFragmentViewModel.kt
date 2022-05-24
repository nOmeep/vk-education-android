package com.example.viewed.screens.futures

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewed.data.retrofit.RetrofitRepository
import com.example.viewed.models.UpcomingModel
import kotlinx.coroutines.launch
import retrofit2.Response

class FutureFragmentViewModel: ViewModel() {

        private val repository = RetrofitRepository()
        val myUpcoming: MutableLiveData<Response<UpcomingModel>> = MutableLiveData()

        fun getUpcoming() {
            viewModelScope.launch {
                try {

                    myUpcoming.value = repository.getUpcoming()

                } catch (e: Exception) {
                    Log.e("ERROR", e.message.toString())
                }
            }
        }
    }
