package com.retronow.app.ui.home

import android.content.Context
import android.content.SharedPreferences
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
    val error: String? = null,
    val showOnlyRetrograde: Boolean = true // Default to showing only retrograde planets
)

/**
 * ViewModel for the home screen
 */
class HomeViewModel(
    private val calculator: RetrogradeCalculator,
    private val context: Context? = null
) : ViewModel() {
    
    private val prefs: SharedPreferences? = context?.getSharedPreferences("retro_now_prefs", Context.MODE_PRIVATE)
    private val defaultShowOnlyRetrograde = true
    
    private val _uiState = MutableStateFlow(
        HomeUiState(
            showOnlyRetrograde = prefs?.getBoolean("show_only_retrograde", defaultShowOnlyRetrograde) ?: defaultShowOnlyRetrograde
        )
    )
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
                val allStatuses = Planet.ALL_PLANETS.map { planet ->
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
                
                // Filter based on showOnlyRetrograde preference
                val filteredStatuses = if (_uiState.value.showOnlyRetrograde) {
                    allStatuses.filter { it.isInRetrograde }
                } else {
                    allStatuses
                }
                
                _uiState.value = _uiState.value.copy(
                    planetStatuses = filteredStatuses,
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
    
    /**
     * Toggle filter to show only retrograde planets or all planets
     */
    fun toggleShowOnlyRetrograde() {
        val newValue = !_uiState.value.showOnlyRetrograde
        _uiState.value = _uiState.value.copy(showOnlyRetrograde = newValue)
        
        // Persist preference
        prefs?.edit()?.putBoolean("show_only_retrograde", newValue)?.apply()
        
        // Reload with new filter
        loadPlanetStatuses()
    }
}

/**
 * Factory for creating HomeViewModel
 */
class HomeViewModelFactory(
    private val calculator: RetrogradeCalculator,
    private val context: Context? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(calculator, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

