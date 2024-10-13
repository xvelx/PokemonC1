package io.github.xvelx.pokemonc1.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(
    space: Dp = 16.dp
) {
    Spacer(Modifier.height(space))
}

@Composable
fun HorizontalSpacer(
    space: Dp = 16.dp
) {
    Spacer(Modifier.width(space))
}
