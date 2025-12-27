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
            astrologicalMeaning = "Mercury governs communication, thinking, learning, transportation, and technology. In traditional astrology, Mercury retrograde is associated with communication breakdowns, travel delays, and technological issues. It's often seen as a time for reflection, review, and reconsideration rather than starting new projects. During Mercury retrograde, people may experience misunderstandings, delays in contracts or agreements, and issues with electronic devices. However, it's also considered an excellent time for revisiting past projects, reconnecting with old friends, and reflecting on communication patterns. Mercury rules Gemini (curiosity, versatility) and Virgo (analysis, service), influencing how we process information and express ourselves.",
            typicalDuration = "Approximately 3 weeks, occurring 3-4 times per year",
            frequency = "Every 3-4 months"
        )
    }
    
    private fun getVenusInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.VENUS.id,
            planetName = "Venus",
            retrogradeExplanation = "Venus retrograde occurs when Venus appears to move backward in the sky. This happens when Earth overtakes Venus in their orbits around the Sun, creating an apparent backward motion.",
            astrologicalMeaning = "Venus governs love, relationships, beauty, art, money, values, and pleasure. Traditionally, Venus retrograde is associated with relationship issues, financial reconsiderations, and re-evaluating values and desires. It's seen as a time to reflect on love, beauty, and what we truly value. During Venus retrograde, people may re-evaluate relationships, question their values, or experience delays in financial matters. It's often advised to avoid major purchases, starting new relationships, or making significant aesthetic changes. However, it's an excellent time for reconnecting with past loves, reviewing financial plans, and rediscovering what brings true joy. Venus rules Taurus (sensuality, stability) and Libra (harmony, partnership), influencing how we relate to others and appreciate beauty.",
            typicalDuration = "Approximately 6 weeks, occurring every 18 months",
            frequency = "Every 18 months"
        )
    }
    
    private fun getMarsInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.MARS.id,
            planetName = "Mars",
            retrogradeExplanation = "Mars retrograde occurs when Mars appears to move backward relative to the stars. This happens when Earth, moving faster in its orbit, overtakes the slower-moving Mars.",
            astrologicalMeaning = "Mars governs action, energy, desire, passion, aggression, courage, and assertion. Mars retrograde is traditionally associated with energy redirection, frustration, and the need to reconsider actions and motivations. It's often seen as a time when direct action may be blocked, requiring patience and strategic planning. During Mars retrograde, people may feel less motivated, experience delays in projects, or need to revisit past conflicts. It's a time to reflect on how we assert ourselves, what drives us, and whether our actions align with our true desires. Starting new ventures or making aggressive moves is often discouraged, but it's an excellent period for strategic planning and understanding inner motivations. Mars rules Aries (initiative, courage) and co-rules Scorpio (intensity, transformation), influencing how we take action and assert our will.",
            typicalDuration = "Approximately 2-3 months, occurring every 26 months",
            frequency = "Every 26 months (approximately 2 years)"
        )
    }
    
    private fun getJupiterInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.JUPITER.id,
            planetName = "Jupiter",
            retrogradeExplanation = "Jupiter retrograde is an annual occurrence when Jupiter appears to move backward in the sky. This is due to Earth's faster orbital motion relative to the slower-moving Jupiter.",
            astrologicalMeaning = "Jupiter governs expansion, growth, optimism, philosophy, higher learning, travel, and abundance. Jupiter retrograde is traditionally associated with internal growth, philosophical reflection, and reconsidering beliefs and expansion. It's seen as a time to focus on inner wisdom rather than external opportunities. During Jupiter retrograde, people may question their beliefs, review their philosophies, or find that external expansion feels blocked. It's an excellent time for inner growth, studying, and developing wisdom rather than seeking new opportunities. This period encourages reflection on what truly brings meaning and expansion to life. Jupiter rules Sagittarius (exploration, philosophy) and co-rules Pisces (spirituality, compassion), influencing our quest for meaning and growth.",
            typicalDuration = "Approximately 4 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getSaturnInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.SATURN.id,
            planetName = "Saturn",
            retrogradeExplanation = "Saturn retrograde occurs annually when Saturn appears to move backward relative to Earth. This is a regular part of Saturn's apparent motion as Earth orbits faster.",
            astrologicalMeaning = "Saturn governs structure, discipline, responsibility, limitations, boundaries, time, and karma. Saturn retrograde is traditionally associated with reviewing responsibilities, structures, and limitations. It's seen as a time for internal reflection on discipline, boundaries, and long-term goals. During Saturn retrograde, people may feel less external pressure but are encouraged to examine their internal structures, responsibilities, and long-term plans. It's a time to review commitments, reconsider boundaries, and work on inner discipline. This period often brings lessons about what structures serve us and which ones need revision. Saturn rules Capricorn (ambition, structure) and co-rules Aquarius (innovation, community), influencing how we build lasting foundations and handle responsibility.",
            typicalDuration = "Approximately 4.5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getUranusInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.URANUS.id,
            planetName = "Uranus",
            retrogradeExplanation = "Uranus retrograde is an annual occurrence when Uranus appears to move backward in the sky. This happens as Earth's faster orbit overtakes the slower-moving Uranus.",
            astrologicalMeaning = "Uranus governs innovation, revolution, freedom, individuality, sudden change, technology, and awakening. Uranus retrograde is traditionally associated with internalizing change, reflecting on innovation, and reconsidering freedom and individuality. It's seen as a time for internal revolution rather than external upheaval. During Uranus retrograde, people may experience internal shifts in perspective, question their need for freedom, or reconsider how they express their uniqueness. External changes may feel blocked, but internal transformation is encouraged. This period is excellent for breaking free from limiting patterns and embracing authentic self-expression. Uranus rules Aquarius (innovation, humanitarianism), influencing how we seek freedom and contribute to collective progress.",
            typicalDuration = "Approximately 5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getNeptuneInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.NEPTUNE.id,
            planetName = "Neptune",
            retrogradeExplanation = "Neptune retrograde occurs annually when Neptune appears to move backward relative to Earth. This is due to Earth's faster orbital motion.",
            astrologicalMeaning = "Neptune governs dreams, intuition, spirituality, illusion, creativity, compassion, and transcendence. Neptune retrograde is traditionally associated with inner spiritual work, dream analysis, and reconsidering illusions and ideals. It's seen as a time for internal reflection on intuition, creativity, and transcendence. During Neptune retrograde, people may become more aware of illusions, deepen their spiritual practice, or reconnect with their creative inspiration. It's a time to examine what we idealize, work on spiritual development, and develop greater compassion. This period encourages dissolving boundaries between self and others, exploring the mystical, and finding truth beyond appearances. Neptune rules Pisces (intuition, compassion), influencing our connection to the divine and the collective unconscious.",
            typicalDuration = "Approximately 5 months, occurring annually",
            frequency = "Once per year"
        )
    }
    
    private fun getPlutoInfo(): PlanetInfo {
        return PlanetInfo(
            planetId = Planet.PLUTO.id,
            planetName = "Pluto",
            retrogradeExplanation = "Pluto retrograde is an annual occurrence when Pluto appears to move backward in the sky. This happens as Earth's faster orbit overtakes the slower-moving Pluto.",
            astrologicalMeaning = "Pluto governs transformation, power, death and rebirth, the unconscious, regeneration, and deep psychological processes. Pluto retrograde is traditionally associated with internal transformation, deep psychological work, and reconsidering power dynamics. It's seen as a time for inner healing and processing deep-seated issues. During Pluto retrograde, people may experience intense internal transformation, confront shadow aspects of themselves, or work through power dynamics in relationships. External power struggles may feel less intense, but internal work is profound. This period encourages releasing what no longer serves, healing deep wounds, and transforming from within. Pluto rules Scorpio (transformation, intensity), influencing our capacity for deep change and regeneration.",
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

