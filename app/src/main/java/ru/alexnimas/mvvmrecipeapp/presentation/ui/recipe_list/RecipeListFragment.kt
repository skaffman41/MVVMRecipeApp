package ru.alexnimas.mvvmrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.alexnimas.mvvmrecipeapp.presentation.components.RecipeCard

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value

                Column {
                    TextField(
                        value = viewModel.query.value,
                        onValueChange = { newValue -> viewModel.onQueryChanged(newValue) }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                        itemsIndexed(items = recipes) { index, recipe ->
                            RecipeCard(recipe = recipe) { }
                        }
                    }
                }
            }
        }
    }
}