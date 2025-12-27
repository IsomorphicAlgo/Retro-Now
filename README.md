# Retro-Now

A lightweight Android application that displays the retrograde status of planets and provides educational content about planetary retrogrades.

## Overview

Retro-Now is an educational app focused on displaying the current retrograde status of major planets (Mercury, Venus, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto). The app provides informative content about what retrograde means and its traditional astrological interpretations, while maintaining a clear educational (not predictive) focus.

## Key Features

- **Current Retrograde Status**: Real-time display of retrograde status for all major planets
- **Planet Details**: In-depth information about each planet's retrograde periods and meanings
- **Calendar View**: Visual timeline showing past and future retrograde periods
- **Educational Content**: Resources explaining retrograde and its significance
- **Offline Support**: Works completely offline after initial data load
- **Notifications**: Optional alerts for retrograde entry/exit dates
- **Dark/Light Mode**: Full theme support
- **Accessibility**: Screen reader support and adjustable text sizes

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database
- **Navigation**: Jetpack Navigation Component
- **Async Operations**: Coroutines & Flow
- **Design**: Material Design 3

## Requirements

- Android 8.0 (API 26) or higher
- Minimal data usage
- Fast load times (< 2 seconds on mid-range devices)
- Battery efficient

## Project Status

**Current Stage**: Stage 5 - Learn & Settings Screens ✅ Completed  
**Next Stage**: Stage 6 - Notifications (Optional Feature)

See [ITERATIVE_PLAN.md](./ITERATIVE_PLAN.md) for detailed development stages.

### Stage 0 Completed
- Android project structure created
- Kotlin + Jetpack Compose configured
- MVVM architecture setup
- All dependencies configured
- Git repository initialized

### Stage 1 Completed
- Room database with entities and DAOs
- Repository pattern implemented
- Pre-calculated retrograde data (2024-2030)
- Educational content structure
- Database initialization system

### Stage 2 Completed
- Home screen with planet status tiles
- HomeViewModel with state management
- Navigation Component setup
- Visual status indicators
- Days remaining display
- Loading and error states

### Stage 3 Completed
- Planet detail screen with educational content
- Planet selector dialog
- Current retrograde status display
- Upcoming and past retrograde periods
- Navigation from home to detail screen

### Stage 4 Completed
- Calendar month view with day grid
- Month navigation (previous/next buttons)
- "Today" button to jump to current month
- Retrograde visualization with colored lines for each planet
- Planet color legend
- Handles month boundaries correctly
- Shows retrograde periods across adjacent months

### Stage 5 Completed
- Learn screen with educational content about retrogrades
- Educational resource links (clickable, opens in browser)
- Disclaimer section
- Settings screen with theme toggle (light/dark mode)
- About section with app information
- Notification settings placeholder
- Menu dropdown navigation from home screen
- Complete navigation between all screens
- Theme preference persistence

### Fixes & Improvements (New!)
- **Build System Repair**: Fixed file locking issues and Gradle version mismatches.
- **Modernization**: Migrated from deprecated `kapt` to `KSP` (Kotlin Symbol Processing) to fix Java 21 compatibility issues.
- **Assets**: Added placeholder launcher icons to resolve build errors.
- **UI Improvements**:
  - Planet tiles now auto-size to content (fixed blank space issue)
  - Tiles are now square while maintaining single-column layout
  - Added filter option to show only planets in retrograde (default on first open)
  - Filter preference is persisted across app sessions
  - Added toggle button in top bar to switch between filtered and all planets view

## Development

This project follows an iterative development approach. Each stage is completed, tested, and approved before proceeding to the next.

### Project Structure

```
app/
├── ui/              # Screens and UI components
├── data/            # Database, repositories
├── domain/          # Models, use cases
└── utils/           # Calculations, helpers
```

## Design

The app features a blue/green/purple color scheme with background images for each key screen. The design is clean, minimalist, and astronomy-inspired.

## License

[To be determined]

## Contributing

This is a personal project. Contributions are not currently being accepted.

---

**Last Updated**: 2024-12-19 (UI improvements and filter functionality)
