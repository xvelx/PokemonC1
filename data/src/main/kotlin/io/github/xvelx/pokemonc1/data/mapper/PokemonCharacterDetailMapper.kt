package io.github.xvelx.pokemonc1.data.mapper

import java.util.Locale
import javax.inject.Inject
import io.github.xvelx.pokemonc1.data.api.dto.PokemonCharacterDetail as CharacterDetailDto
import io.github.xvelx.pokemonc1.domain.model.PokemonCharacterDetail as CharacterDetailModel

class PokemonCharacterDetailMapper @Inject constructor() {

    fun map(characterDetailDto: CharacterDetailDto): CharacterDetailModel {
        return CharacterDetailModel(
            id = characterDetailDto.id,
            name = characterDetailDto.name.uppercase(),
            imageUrl = String.format(
                Locale.getDefault(),
                IMAGE_URL_TEMPLATE,
                characterDetailDto.id
            ),
            weight = characterDetailDto.weight,
            height = characterDetailDto.height,
            abilities = characterDetailDto.abilities.map {
                it.ability.name.uppercase()
            },
            states = characterDetailDto.stats.associate {
                it.stat.name.uppercase() to it.baseStat
            },
            cries = characterDetailDto.cries.latest
        )
    }
}
