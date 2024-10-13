package io.github.xvelx.data.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

data class PokemonCharacterDetail(
    @JsonProperty("abilities") val abilities: List<Ability>,
    @JsonProperty("base_experience") val baseExperience: Long,
    @JsonProperty("cries") val cries: Cries,
    @JsonProperty("height") val height: Long,
    @JsonProperty("id") val id: Long,
    @JsonProperty("moves") val moves: List<Moves>,
    @JsonProperty("name") val name: String,
    @JsonProperty("order") val order: Long,
    @JsonProperty("stats") val stats: List<Stat>,
    @JsonProperty("weight") val weight: Long,
    @JsonProperty("sprites") val sprites: Sprites,
)

data class Ability(
    @JsonProperty("ability") val ability: AbilityDetail,
    @JsonProperty("is_hidden") val isHidden: Boolean,
    @JsonProperty("slot") val slot: Long,
)

data class AbilityDetail(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: URL,
)

data class Cries(
    @JsonProperty("latest") val latest: URL,
    @JsonProperty("legacy") val legacy: URL,
)

data class Moves(
    @JsonProperty("move") val move: Move,
    @JsonProperty("version_group_details") val versionGroupDetails: List<VersionGroupDetail>,
)

data class Move(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: URL?,
)

data class VersionGroupDetail(
    @JsonProperty("level_learned_at") val levelLearnedAt: Long,
    @JsonProperty("move_learn_method") val moveLearnMethod: MoveLearnMethod,
    @JsonProperty("version_group") val versionGroup: VersionGroup,
)

data class MoveLearnMethod(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: URL?,
)

data class VersionGroup(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: URL?,
)

data class Stat(
    @JsonProperty("base_stat") val baseStat: Long,
    @JsonProperty("effort") val effort: Long,
    @JsonProperty("stat") val stat: StatDetail,
)

data class StatDetail(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: URL?,
)

data class Sprites(
    @JsonProperty("back_default") val backDefault: URL?,
    @JsonProperty("back_female") val backFemale: URL?,
    @JsonProperty("back_shiny") val backShiny: URL?,
    @JsonProperty("back_shiny_female") val backShinyFemale: URL?,
    @JsonProperty("front_default") val frontDefault: URL?,
    @JsonProperty("front_female") val frontFemale: URL?,
    @JsonProperty("front_shiny") val frontShiny: URL?,
    @JsonProperty("front_shiny_female") val frontShinyFemale: URL?
)
