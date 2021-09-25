package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.mvvmrecipeapp.presentation.components.CircularProgressBar
import ru.alexnimas.mvvmrecipeapp.presentation.components.FoodCategoryChip
import ru.alexnimas.mvvmrecipeapp.presentation.components.RecipeCard
import ru.alexnimas.mvvmrecipeapp.presentation.components.SearchAppBar

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent {

            val recipes = viewModel.recipes.value

            val query = viewModel.query.value

            val selectedCategory = viewModel.selectedCategory.value

            val loading = viewModel.loading.value

            Column {

                SearchAppBar(
                    query = query,
                    onQueryChanged = viewModel::onQueryChanged,
                    onExecuteSearch = viewModel::newSearch,
                    categories = getAllFoodCategories(),
                    selectedCategory = selectedCategory,
                    onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                    scrollPosition = viewModel.categoryScrollPosition,
                    onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {})
                        }
                    }
                    CircularProgressBar(isDisplayed = loading)
                }
            }
        }
        return composeView
    }
}