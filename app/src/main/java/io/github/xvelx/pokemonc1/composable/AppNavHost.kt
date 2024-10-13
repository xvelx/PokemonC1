package io.github.xvelx.pokemonc1.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.xvelx.pokemonc1.screen.detail.CharacterDetailScreen
import io.github.xvelx.pokemonc1.screen.list.CharacterListScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavHost() {
    LocalNavController.current?.let { navHost ->
        NavHost(navHost, startDestination = Screens.List) {
            composable<Screens.List> { CharacterListScreen() }
            composable<Screens.Detail> { backStackEntry ->
                val detailScreen = backStackEntry.toRoute<Screens.Detail>()
                CharacterDetailScreen(detailScreen.id)
            }
        }
    }
}

sealed interface Screens {
    @Serializable
    data object List : Screens

    @Serializable
    data class Detail(val id: Int) : Screens
}
