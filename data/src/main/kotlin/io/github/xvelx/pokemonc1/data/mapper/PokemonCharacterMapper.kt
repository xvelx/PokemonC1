package io.github.xvelx.pokemonc1.data.mapper

import java.util.Locale
import io.github.xvelx.pokemonc1.data.api.dto.PokemonCharacter as CharacterDto
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacter as CharacterModel
import javax.inject.Inject

class PokemonCharacterMapper @Inject constructor() {

    fun map(characters: List<CharacterDto>): List<CharacterModel> = characters.map {
        val characterId = ID_EXTRACTOR.find(it.url)?.groupValues?.getOrNull(1)?.toInt() ?: -1
        CharacterModel(
            characterId,
            it.name.uppercase(),
            String.format(Locale.getDefault(), IMAGE_URL_TEMPLATE, characterId)
        )
    }

    private companion object {
        val ID_EXTRACTOR = "/(\\d+)/$".toRegex()
    }
}
