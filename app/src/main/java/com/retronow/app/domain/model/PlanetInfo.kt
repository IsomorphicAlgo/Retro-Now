package com.retronow.app.domain.model

/**
 * Educational information about a planet's retrograde
 */
data class PlanetInfo(
    val planetId: String,
    val planetName: String,
    val retrogradeExplanation: String,
    val astrologicalMeaning: String,
    val typicalDuration: String,
    val frequency: String
)

