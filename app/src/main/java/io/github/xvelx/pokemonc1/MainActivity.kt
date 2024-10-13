package io.github.xvelx.pokemonc1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.xvelx.domain.SampleRepository
import io.github.xvelx.pokemonc1.ui.theme.PokemonC1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repo: SampleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonC1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
//                val response = pokemonData.apiClient.fetchCharacters(0)
//                println(response)
//
//                val response2 = pokemonData.apiClient.fetchCharacterDetail(1)
//                println(response2)

               repo.fetchPokemonCharactersList()
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    viewModel: GreetingViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name! -- ${viewModel.s()}",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonC1Theme {
        Greeting("Android")
    }
}

@HiltViewModel
class GreetingViewModel @Inject constructor() : ViewModel() {

    fun s() = "Ssere"
}