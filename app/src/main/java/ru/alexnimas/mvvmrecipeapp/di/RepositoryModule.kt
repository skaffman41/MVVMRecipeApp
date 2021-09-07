package ru.alexnimas.mvvmrecipeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexnimas.mvvmrecipeapp.network.RecipeService
import ru.alexnimas.mvvmrecipeapp.network.model.RecipeDtoMapper
import ru.alexnimas.mvvmrecipeapp.repository.RecipeRepository
import ru.alexnimas.mvvmrecipeapp.repository.RecipeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeService, recipeDtoMapper)
    }
}