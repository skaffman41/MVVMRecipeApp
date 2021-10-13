package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.alexnimas.mvvmrecipeapp.domain.model.Recipe
import ru.alexnimas.mvvmrecipeapp.repository.RecipeRepository
import javax.inject.Inject

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val recipes = mutableStateOf<List<Recipe>>(ArrayList())

    val query = mutableStateOf("")

    val selectedCategory = mutableStateOf<FoodCategory?>(null)

    var categoryScrollPosition: Float = 0f

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    var recipeListScrollPosition = 0

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            delay(2000)
            val result = repository.search(
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()

                delay(1000)

                if (page.value > 1) {
                    val result = repository.search(page = page.value, query = query.value)
                    appendRecipes(result)
                }
                loading.value = false
            }
        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if (selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }
}
