package ru.alexnimas.mvvmrecipeapp.repository

import ru.alexnimas.mvvmrecipeapp.domain.model.Recipe
import ru.alexnimas.mvvmrecipeapp.network.RecipeService
import ru.alexnimas.mvvmrecipeapp.network.model.RecipeDtoMapper

class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.toDomainList(recipeService.search(token, page, query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token, id))
    }
}