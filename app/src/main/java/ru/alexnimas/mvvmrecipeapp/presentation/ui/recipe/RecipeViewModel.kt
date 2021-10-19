package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.alexnimas.mvvmrecipeapp.domain.model.Recipe
import ru.alexnimas.mvvmrecipeapp.repository.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor (
    private val recipeRepository: RecipeRepository,
) : ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is RecipeEvent.GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true

        delay(1000)

        val recipe = recipeRepository.get(id = id)
        this.recipe.value = recipe

        loading.value = false
    }
}