package com.example.viewed.api.items

data class MoviePage(
    val page: Int,
    val results: List<Info>,
    val total_pages: Int,
    val total_results: Int
) {
    data class Info(
        val adult: Boolean,
        val backdrop_path: String?,
        val genre_ids: List<Float>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String?,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Float,
        val vote_count: Int
    )
}
