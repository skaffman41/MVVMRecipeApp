package ru.alexnimas.mvvmrecipeapp.presentation.navigation

sealed class Screen(val route: String) {
    object RecipeList : Screen("recipeList")
    object Recipe : Screen("recipeDetail")
}