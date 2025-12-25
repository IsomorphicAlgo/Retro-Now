package com.retronow.app.domain.model

/**
 * Represents a planet in the solar system
 */
data class Planet(
    val id: String,
    val name: String,
    val displayName: String,
    val order: Int // Order from sun
) {
    companion object {
        val MERCURY = Planet("mercury", "Mercury", "Mercury", 1)
        val VENUS = Planet("venus", "Venus", "Venus", 2)
        val MARS = Planet("mars", "Mars", "Mars", 4)
        val JUPITER = Planet("jupiter", "Jupiter", "Jupiter", 5)
        val SATURN = Planet("saturn", "Saturn", "Saturn", 6)
        val URANUS = Planet("uranus", "Uranus", "Uranus", 7)
        val NEPTUNE = Planet("neptune", "Neptune", "Neptune", 8)
        val PLUTO = Planet("pluto", "Pluto", "Pluto", 9)
        
        val ALL_PLANETS = listOf(
            MERCURY, VENUS, MARS, JUPITER, SATURN, URANUS, NEPTUNE, PLUTO
        )
    }
}

