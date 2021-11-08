package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.alexnimas.mvvmrecipeapp.presentation.components.IMAGE_HEIGHT
import ru.alexnimas.mvvmrecipeapp.presentation.components.LoadingRecipeShimmer
import ru.alexnimas.mvvmrecipeapp.presentation.components.RecipeView
import ru.alexnimas.mvvmrecipeapp.presentation.theme.AppTheme

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeScreen(
    isDarkTheme: Boolean,
    recipeId: Int?,
    viewModel: RecipeViewModel
) {
    if (recipeId == null) {
        TODO()
    } else {
        val onLoad = viewModel.onLoad.value
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
        val loading = viewModel.loading.value

        val recipe = viewModel.recipe.value

        val scaffoldState = rememberScaffoldState()

        AppTheme(
            displayProgressBar = loading,
            scaffoldState = scaffoldState,
            darkTheme = isDarkTheme,
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    scaffoldState.snackbarHostState
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (loading && recipe == null) LoadingRecipeShimmer(imageHeight = IMAGE_HEIGHT.dp)
                    else if (!loading && recipe == null && onLoad) TODO("Invalid recipe")
                    else recipe?.let { RecipeView(recipe = it) }
                }
            }
        }
    }
}