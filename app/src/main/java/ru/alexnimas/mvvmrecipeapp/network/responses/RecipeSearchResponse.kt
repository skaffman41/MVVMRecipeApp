package ru.alexnimas.mvvmrecipeapp.network.responses

import com.google.gson.annotations.SerializedName
import ru.alexnimas.mvvmrecipeapp.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)