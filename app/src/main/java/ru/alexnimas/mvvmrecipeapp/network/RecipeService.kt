package ru.alexnimas.mvvmrecipeapp.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.alexnimas.mvvmrecipeapp.network.model.RecipeDto
import ru.alexnimas.mvvmrecipeapp.network.responses.RecipeSearchResponse

interface RecipeService {
    @GET("search")
    suspend fun search(
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse

    @GET("get")
    suspend fun get(
        @Query("id") id: Int
    ): RecipeDto
}