package ru.alexnimas.mvvmrecipeapp.repository

import ru.alexnimas.mvvmrecipeapp.domain.model.Recipe

interface RecipeRepository {
    suspend fun search(page: Int, query: String): List<Recipe>
    suspend fun get(id: Int): Recipe
}