package com.example.retrofi2.model

data class UserData(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)