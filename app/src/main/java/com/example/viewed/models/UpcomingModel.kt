package com.example.viewed.models


  data class UpcomingModel(
        val dates: Dates,
        val page: Int,
        val results: List<UpcomingItemModel>,
        val total_pages: Int,
        val total_results: Int
    )
