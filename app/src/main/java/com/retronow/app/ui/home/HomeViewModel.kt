package com.retronow.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.retronow.app.data.database.DatabaseProvider
import com.retronow.app.data.repository.RetrogradeRepositoryImpl
import com.retronow.app.domain.model.Planet
import com.retronow.app.domain.model.RetrogradePeriod
import com.retronow.app.utils.RetrogradeCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * UI state for planet retrograde status
 */
data class PlanetStatus(
    val planet: Planet,
    val isInRetrograde: Boolean,
    val currentPeriod: RetrogradePeriod?,
    val daysRemaining: Int
)

/**
 * UI state for home screen
 */
data class HomeUiState(
    val planetStatuses: List<PlanetStatus> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

/**
 * ViewModel for the home screen
 */
class HomeViewModel(
    private val calculator: RetrogradeCalculator
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadPlanetStatuses()
    }
    
    /**
     * Load retrograde status for all planets
     */
    fun loadPlanetStatuses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val today = LocalDate.now()
                val statuses = Planet.ALL_PLANETS.map { planet ->
                    val isInRetrograde = calculator.isInRetrograde(planet.id, today)
                    val currentPeriod = calculator.getCurrentRetrogradePeriod(planet.id, today)
                    val daysRemaining = currentPeriod?.daysRemaining(today) ?: 0
                    
                    PlanetStatus(
                        planet = planet,
                        isInRetrograde = isInRetrograde,
                        currentPeriod = currentPeriod,
                        daysRemaining = daysRemaining
                    )
                }
                
                _uiState.value = HomeUiState(
                    planetStatuses = statuses,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load planet statuses"
                )
            }
        }
    }
    
    /**
     * Refresh planet statuses
     */
    fun refresh() {
        loadPlanetStatuses()
    }
}

/**
 * Factory for creating HomeViewModel
 */
class HomeViewModelFactory(
    private val calculator: RetrogradeCalculator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(calculator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

