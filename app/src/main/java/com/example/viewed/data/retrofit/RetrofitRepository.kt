package com.example.viewed.data.retrofit







import android.os.Build
import com.example.viewed.data.retrofit.api.RetrofitInstance
import com.example.viewed.models.MoviesModel
import com.example.viewed.models.UpcomingModel
import retrofit2.Response


class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel> {
        return RetrofitInstance.api.getPopularMovie()
        }
    suspend fun getUpcoming(): Response<UpcomingModel> {
        return RetrofitInstance.api.getUpcomingMovie()
        }


}