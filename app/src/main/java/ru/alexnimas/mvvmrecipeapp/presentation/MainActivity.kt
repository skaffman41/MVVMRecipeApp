package ru.alexnimas.mvvmrecipeapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.alexnimas.mvvmrecipeapp.presentation.navigation.Screen
import ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe.RecipeScreen
import ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe.RecipeViewModel
import ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListScreen
import ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
                composable(route = Screen.RecipeList.route) { RecipeList(navBackStackEntry = it) }
                composable(route = Screen.Recipe.route) { Recipe(navBackStackEntry = it) }
            }
        }
    }

    @Composable
    private fun RecipeList(navBackStackEntry: NavBackStackEntry) {
        val factory = HiltViewModelFactory(AmbientContext.current, navBackStackEntry)
        val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
        RecipeListScreen(
            isDarkTheme = (application as App).isDark.value,
            onToggleTheme = (application as App)::toggleLightTheme,
            viewModel = viewModel
        )
    }

    @Composable
    private fun Recipe(navBackStackEntry: NavBackStackEntry) {
        val factory = HiltViewModelFactory(AmbientContext.current, navBackStackEntry)
        val viewModel: RecipeViewModel = viewModel("RecipeDetailViewModel", factory)
        RecipeScreen(
            isDarkTheme = (application as App).isDark.value,
            recipeId = 1,
            viewModel = viewModel
        )
    }
}