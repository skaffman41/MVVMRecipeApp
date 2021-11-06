package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.alexnimas.mvvmrecipeapp.presentation.components.RecipeList
import ru.alexnimas.mvvmrecipeapp.presentation.components.SearchAppBar
import ru.alexnimas.mvvmrecipeapp.presentation.theme.AppTheme

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun RecipeListScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    viewModel: RecipeListViewModel
) {
    val recipes = viewModel.recipes.value

    val query = viewModel.query.value

    val selectedCategory = viewModel.selectedCategory.value

    val loading = viewModel.loading.value

    val page = viewModel.page.value

    val scaffoldState = rememberScaffoldState()

    AppTheme(
        displayProgressBar = loading,
        scaffoldState = scaffoldState,
        darkTheme = isDarkTheme,
    ) {

        Scaffold(
            topBar = {
                SearchAppBar(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onExecuteSearch = { viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent) },
                    categories = getAllFoodCategories(),
                    selectedCategory = selectedCategory,
                    onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                    onToggleTheme = { onToggleTheme.invoke() }
                )
            },
            scaffoldState = scaffoldState,
            snackbarHost = {
                scaffoldState.snackbarHostState
            },

            ) {
            RecipeList(
                loading = loading,
                recipes = recipes,
                onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                page = page,
                onTriggerNextPage = { viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent) },
                onNavigateToRecipeDetailScreen = {
                    TODO()
                }
            )
        }
    }
}