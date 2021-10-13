package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list

sealed class RecipeListEvent {
    object NewSearchEvent: RecipeListEvent()
    object NextPageEvent: RecipeListEvent()
}