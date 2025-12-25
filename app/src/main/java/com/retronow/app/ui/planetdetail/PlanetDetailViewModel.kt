package com.retronow.app.ui.planetdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.retronow.app.data.educational.PlanetInfoProvider
import com.retronow.app.data.repository.RetrogradeRepositoryImpl
import com.retronow.app.domain.model.Planet
import com.retronow.app.domain.model.PlanetInfo
import com.retronow.app.domain.model.RetrogradePeriod
import com.retronow.app.utils.RetrogradeCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * UI state for planet detail screen
 */
data class PlanetDetailUiState(
    val selectedPlanetId: String? = null,
    val planetInfo: PlanetInfo? = null,
    val isInRetrograde: Boolean = false,
    val currentPeriod: RetrogradePeriod? = null,
    val upcomingPeriods: List<RetrogradePeriod> = emptyList(),
    val pastPeriods: List<RetrogradePeriod> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel for the planet detail screen
 */
class PlanetDetailViewModel(
    private val calculator: RetrogradeCalculator,
    private val repository: RetrogradeRepositoryImpl
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PlanetDetailUiState())
    val uiState: StateFlow<PlanetDetailUiState> = _uiState.asStateFlow()
    
    /**
     * Load planet detail data
     */
    fun loadPlanetDetail(planetId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                selectedPlanetId = planetId,
                isLoading = true,
                error = null
            )
            
            try {
                val today = LocalDate.now()
                val planet = Planet.ALL_PLANETS.find { it.id == planetId }
                
                if (planet == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Planet not found"
                    )
                    return@launch
                }
                
                // Get planet info
                val planetInfo = PlanetInfoProvider.getPlanetInfo(planetId)
                
                // Get current retrograde status
                val isInRetrograde = calculator.isInRetrograde(planetId, today)
                val currentPeriod = calculator.getCurrentRetrogradePeriod(planetId, today)
                
                // Get upcoming periods (next 2 years)
                val endDate = today.plusYears(2)
                val allUpcomingPeriods = repository.getRetrogradePeriods(planetId, today, endDate)
                val upcomingPeriods = allUpcomingPeriods.filter { 
                    it.startDate.isAfter(today) 
                }.take(5) // Show next 5 periods
                
                // Get past periods (last year)
                val startDate = today.minusYears(1)
                val allPastPeriods = repository.getRetrogradePeriods(planetId, startDate, today)
                val pastPeriods = allPastPeriods.filter { 
                    it.endDate.isBefore(today) 
                }.take(5) // Show last 5 periods
                
                _uiState.value = PlanetDetailUiState(
                    selectedPlanetId = planetId,
                    planetInfo = planetInfo,
                    isInRetrograde = isInRetrograde,
                    currentPeriod = currentPeriod,
                    upcomingPeriods = upcomingPeriods,
                    pastPeriods = pastPeriods,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load planet details"
                )
            }
        }
    }
    
    /**
     * Change selected planet
     */
    fun selectPlanet(planetId: String) {
        loadPlanetDetail(planetId)
    }
}

/**
 * Factory for creating PlanetDetailViewModel
 */
class PlanetDetailViewModelFactory(
    private val calculator: RetrogradeCalculator,
    private val repository: RetrogradeRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanetDetailViewModel(calculator, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

