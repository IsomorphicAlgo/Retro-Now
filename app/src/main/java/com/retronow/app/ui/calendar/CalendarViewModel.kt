package com.retronow.app.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.retronow.app.data.repository.RetrogradeRepositoryImpl
import com.retronow.app.domain.model.Planet
import com.retronow.app.domain.model.RetrogradePeriod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/**
 * Represents a day in the calendar with its retrograde status
 */
data class CalendarDay(
    val date: LocalDate,
    val isCurrentMonth: Boolean,
    val isToday: Boolean,
    val retrogradePlanets: List<Planet> = emptyList()
)

/**
 * UI state for calendar screen
 */
data class CalendarUiState(
    val currentMonth: YearMonth = YearMonth.now(),
    val calendarDays: List<CalendarDay> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

/**
 * ViewModel for the calendar screen
 */
class CalendarViewModel(
    private val repository: RetrogradeRepositoryImpl
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()
    
    init {
        loadCalendarData()
    }
    
    /**
     * Load calendar data for the current month
     */
    fun loadCalendarData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val month = _uiState.value.currentMonth
                // Include adjacent months to show retrograde periods for days from previous/next month
                val previousMonth = month.minusMonths(1)
                val nextMonth = month.plusMonths(1)
                val startDate = previousMonth.atDay(1)
                val endDate = nextMonth.atEndOfMonth()
                
                // Get all retrograde periods for this range
                val retrogradePeriods = repository.getAllPeriodsInRange(startDate, endDate)
                
                // Build calendar days
                val days = buildCalendarDays(month, retrogradePeriods)
                
                _uiState.value = _uiState.value.copy(
                    calendarDays = days,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load calendar data"
                )
            }
        }
    }
    
    /**
     * Navigate to previous month
     */
    fun previousMonth() {
        val newMonth = _uiState.value.currentMonth.minusMonths(1)
        _uiState.value = _uiState.value.copy(currentMonth = newMonth)
        loadCalendarData()
    }
    
    /**
     * Navigate to next month
     */
    fun nextMonth() {
        val newMonth = _uiState.value.currentMonth.plusMonths(1)
        _uiState.value = _uiState.value.copy(currentMonth = newMonth)
        loadCalendarData()
    }
    
    /**
     * Navigate to current month
     */
    fun goToToday() {
        val today = YearMonth.now()
        _uiState.value = _uiState.value.copy(currentMonth = today)
        loadCalendarData()
    }
    
    /**
     * Build calendar days for a month
     */
    private fun buildCalendarDays(
        month: YearMonth,
        retrogradePeriods: List<RetrogradePeriod>
    ): List<CalendarDay> {
        val days = mutableListOf<CalendarDay>()
        val today = LocalDate.now()
        
        // Get first day of month and first day of week
        val firstDayOfMonth = month.atDay(1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek
        
        // Calculate how many days from previous month to show
        // DayOfWeek: Monday=1, Tuesday=2, ..., Sunday=7
        // For calendar starting on Sunday, we need to map:
        // Sunday (7) -> 0
        // Monday (1) -> 1
        // ...
        // Saturday (6) -> 6
        val daysFromPreviousMonth = if (firstDayOfWeek == DayOfWeek.SUNDAY) 0 else firstDayOfWeek.value
        
        // Add days from previous month
        if (daysFromPreviousMonth > 0) {
            val previousMonth = month.minusMonths(1)
            val daysInPreviousMonth = previousMonth.lengthOfMonth()
            for (i in daysFromPreviousMonth downTo 1) {
                val date = previousMonth.atDay(daysInPreviousMonth - i + 1)
                val retrogradePlanets = getRetrogradePlanetsForDate(date, retrogradePeriods)
                days.add(
                    CalendarDay(
                        date = date,
                        isCurrentMonth = false,
                        isToday = date == today,
                        retrogradePlanets = retrogradePlanets
                    )
                )
            }
        }
        
        // Add days from current month
        val daysInMonth = month.lengthOfMonth()
        for (day in 1..daysInMonth) {
            val date = month.atDay(day)
            val retrogradePlanets = getRetrogradePlanetsForDate(date, retrogradePeriods)
            days.add(
                CalendarDay(
                    date = date,
                    isCurrentMonth = true,
                    isToday = date == today,
                    retrogradePlanets = retrogradePlanets
                )
            )
        }
        
        // Add days from next month to fill the last week
        val totalDays = days.size
        val remainingDays = (7 - (totalDays % 7)) % 7
        if (remainingDays > 0) {
            val nextMonth = month.plusMonths(1)
            for (day in 1..remainingDays) {
                val date = nextMonth.atDay(day)
                val retrogradePlanets = getRetrogradePlanetsForDate(date, retrogradePeriods)
                days.add(
                    CalendarDay(
                        date = date,
                        isCurrentMonth = false,
                        isToday = date == today,
                        retrogradePlanets = retrogradePlanets
                    )
                )
            }
        }
        
        return days
    }
    
    /**
     * Get list of planets in retrograde for a specific date
     */
    private fun getRetrogradePlanetsForDate(
        date: LocalDate,
        periods: List<RetrogradePeriod>
    ): List<Planet> {
        return periods
            .filter { period ->
                !date.isBefore(period.startDate) && !date.isAfter(period.endDate)
            }
            .mapNotNull { period ->
                Planet.ALL_PLANETS.find { it.id == period.planetId }
            }
    }
}

/**
 * Factory for creating CalendarViewModel
 */
class CalendarViewModelFactory(
    private val repository: RetrogradeRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalendarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
