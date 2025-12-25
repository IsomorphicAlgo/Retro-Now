package com.retronow.app.data.educational

import com.retronow.app.domain.model.Planet
import com.retronow.app.domain.model.PlanetInfo

/**
 * Provides educational content about planets and their retrogrades
 */
object PlanetInfoProvider {
    
    fun getPlanetInfo(planetId: String): PlanetInfo? {
        return when (planetId) {
            Planet.MERCURY.id -> getMercuryInfo()
            Planet.VENUS.id -> getVenusInfo()
            Planet.MARS.id -> getMarsInfo()
            Planet.JUPITER.id -> getJupiterInfo()
            Planet.SATURN.id -> getSaturnInfo()
            Planet.URANUS.id -> getUranusInfo()
            Planet.NEPTUNE.id -> getNeptuneInfo()
            Planet.PLUTO.id -> getPlutoInfo()
            else -> null
        }
    }
    
    fun getAllPlanetInfo(): List<PlanetInfo> {
        return Planet.ALL_PLANETS.mapNotNull { getPlanetInfo(it.id) }
    }
    
    private fun getMercuryInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.MERCURY.id,
            planetName = "Mercury",
            retrogradeExplanation = "Mercury retrograde occurs when Mercury appears to move backward in the sky relative to Earth. This is an optical illusion caused by the relative orbital speeds of Earth and Mercury. From an astronomical perspective, Mercury's orbit brings it closer to Earth, making its apparent motion reverse.",
            astrologicalMeaning = "In traditional astrology, Mercury retrograde is associated with communication breakdowns, travel delays, and technological issues. It's often seen as a time for reflection, review, and reconsideration rather than starting new projects.",
            typicalDuration = "Approximately 3 weeks, occurring 3-4 times per year",
            frequency = "Every 3-4 months"
        )
    }
    
    private fun getVenusInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.VENUS.id,
            planetName = "Venus",
            retrogradeExplanation = "Venus retrograde occurs when Venus appears to move backward in the sky. This happens when Earth overtakes Venus in their orbits around the Sun, creating an apparent backward motion.",
            astrologicalMeaning = "Traditionally, Venus retrograde is associated with relationship issues, financial reconsiderations, and re-evaluating values and desires. It's seen as a time to reflect on love, beauty, and what we truly value.",
            typicalDuration = "Approximately 6 weeks, occurring every 18 months",
            frequency = "Every 18 months"
        )
    }
    
    private fun getMarsInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.MARS.id,
            planetName = "Mars",
            retrogradeExplanation = "Mars retrograde occurs when Mars appears to move backward relative to the stars. This happens when Earth, moving faster in its orbit, overtakes the slower-moving Mars.",
            astrologicalMeaning = "Mars retrograde is traditionally associated with energy redirection, frustration, and the need to reconsider actions and motivations. It's often seen as a time when direct action may be blocked, requiring patience and strategic planning.",
            typicalDuration = "Approximately 2-3 months, occurring every 26 months",
            frequency = "Every 26 months (approximately 2 years)"
        )
    }
    
    private fun getJupiterInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.JUPITER.id,
            planetName = "Jupiter",
            retrogradeExplanation = "Jupiter retrograde is an annual occurrence when Jupiter appears to move backward in the sky. This is due to Earth's faster orbital motion relative to the slower-moving Jupiter.",
            astrologicalMeaning = "Jupiter retrograde is traditionally associated with internal growth, philosophical reflection, and reconsidering beliefs and expansion. It's seen as a time to focus on inner wisdom rather than external opportunities.",
            typicalDuration = "Approximately 4 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getSaturnInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.SATURN.id,
            planetName = "Saturn",
            retrogradeExplanation = "Saturn retrograde occurs annually when Saturn appears to move backward relative to Earth. This is a regular part of Saturn's apparent motion as Earth orbits faster.",
            astrologicalMeaning = "Saturn retrograde is traditionally associated with reviewing responsibilities, structures, and limitations. It's seen as a time for internal reflection on discipline, boundaries, and long-term goals.",
            typicalDuration = "Approximately 4.5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getUranusInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.URANUS.id,
            planetName = "Uranus",
            retrogradeExplanation = "Uranus retrograde is an annual occurrence when Uranus appears to move backward in the sky. This happens as Earth's faster orbit overtakes the slower-moving Uranus.",
            astrologicalMeaning = "Uranus retrograde is traditionally associated with internalizing change, reflecting on innovation, and reconsidering freedom and individuality. It's seen as a time for internal revolution rather than external upheaval.",
            typicalDuration = "Approximately 5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getNeptuneInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.NEPTUNE.id,
            planetName = "Neptune",
            retrogradeExplanation = "Neptune retrograde occurs annually when Neptune appears to move backward relative to Earth. This is due to Earth's faster orbital motion.",
            astrologicalMeaning = "Neptune retrograde is traditionally associated with inner spiritual work, dream analysis, and reconsidering illusions and ideals. It's seen as a time for internal reflection on intuition, creativity, and transcendence.",
            typicalDuration = "Approximately 5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getPlutoInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.PLUTO.id,
            planetName = "Pluto",
            retrogradeExplanation = "Pluto retrograde is an annual occurrence when Pluto appears to move backward in the sky. This happens as Earth's faster orbit overtakes the slower-moving Pluto.",
            astrologicalMeaning = "Pluto retrograde is traditionally associated with internal transformation, deep psychological work, and reconsidering power dynamics. It's seen as a time for inner healing and processing deep-seated issues.",
            typicalDuration = "Approximately 5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    /**
     * Get general explanation of retrograde motion
     */
    fun getRetrogradeGeneralExplanation(): String {
        return """
            What is Retrograde Motion?
            
            Retrograde motion is an apparent backward movement of a planet in the sky, as observed from Earth. This is an optical illusion caused by the relative orbital speeds of planets.
            
            From an astronomical perspective:
            - Planets orbit the Sun at different speeds
            - Earth, being closer to the Sun, moves faster than outer planets
            - When Earth overtakes an outer planet, that planet appears to move backward
            - Inner planets (Mercury, Venus) can also appear to move backward when they pass between Earth and the Sun
            
            This phenomenon has been observed and documented for thousands of years, dating back to ancient astronomers who carefully tracked planetary movements across the night sky.
            
            Note: This information is for educational and entertainment purposes only. Astronomical observations are scientific facts, while astrological interpretations are cultural traditions.
        """.trimIndent()
    }
}

