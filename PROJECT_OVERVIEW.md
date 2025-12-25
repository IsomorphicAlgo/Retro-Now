# Retro-Now: Project Overview

## Project Description

Retro-Now is a lightweight Android application designed to display the retrograde status of planets and provide educational content about planetary retrogrades. The app focuses on being informative and educational rather than predictive, making it a resource for learning about retrograde phenomena.

## Core Philosophy

- **Educational Focus**: Informative content, not horoscopes or predictions
- **Simplicity**: Works on as many devices as possible
- **Performance**: Battery-efficient, fast, and lightweight
- **Offline-First**: Full functionality without internet connection
- **Accessibility**: Support for all users

## Target Audience

Users interested in:
- Understanding planetary retrograde status
- Learning about retrograde phenomena
- Tracking retrograde periods
- Educational astronomy/astrology content

## Key Screens

1. **Home/Dashboard**: Single-column layout showing all planets with their current retrograde status
2. **Planet Detail**: In-depth information about a selected planet's retrograde
3. **Calendar View**: Month-by-month view showing retrograde periods as colored lines
4. **Learn**: Educational resources and links about retrogrades
5. **Settings**: Theme preferences, notifications, and about section

## Technical Architecture

### Architecture Pattern
- **MVVM (Model-View-ViewModel)**: Separation of concerns, testable, maintainable

### Data Flow
1. **Data Layer**: Room database with pre-calculated retrograde periods
2. **Repository Layer**: Abstracts data sources
3. **ViewModel Layer**: Business logic and state management
4. **UI Layer**: Jetpack Compose screens

### Data Sources
- Retrograde calculations: Custom implementation or integration with Rust CLI tool
- Educational content: Bundled in app resources
- Retrograde periods: Pre-calculated for 5-10 years, stored locally

## Design System

### Color Scheme
- Primary: Deep space blues/purples
- Accents: Bright colors for active retrogrades
- Background: Custom images for each screen (blue/green/purple theme)

### UI Principles
- Clean and minimalist
- Card-based layouts
- Easy to scan at a glance
- Astronomy-inspired aesthetics
- Material Design 3 components

## Performance Goals

- Load time: < 2 seconds on mid-range devices
- Battery efficient: Minimal background processing
- Data usage: Minimal (offline-first approach)
- Compatibility: Android 8.0 (API 26) and above

## Features

### Core Features
- ✅ Display retrograde status for 8 major planets
- ✅ Show entry/exit dates
- ✅ Educational content per planet
- ✅ Calendar view of retrograde periods
- ✅ Offline functionality

### Optional Features
- ⏳ Notifications for retrograde entry/exit
- ⏳ Widget support (future)
- ⏳ Moon phases (future)
- ⏳ Multi-language support (future)

## Development Approach

### Iterative Development
- Build in stages
- Test at each stage
- Get approval before proceeding
- Update documentation after each stage

### Quality Assurance
- Unit tests for calculation logic
- UI tests for critical flows
- Testing on multiple devices and Android versions
- Beta testing before release

## Distribution

- **Primary**: Google Play Store
- **Target**: General public
- **Updates**: Semi-annual updates with new retrograde calculations

## Maintenance Plan

- Semi-annual updates with new retrograde data
- Monitor crash reports and user feedback
- Keep dependencies updated for security
- Address accessibility issues promptly

## Success Metrics

- Fast app launch (< 2 seconds)
- Low battery usage
- Positive user feedback on educational content
- Stable performance across devices
- High accessibility score

---

**Last Updated**: 2024-12-19

## Stage 0 Completion

Stage 0 (Project Setup & Foundation) has been completed. The project now has:
- Complete Android project structure
- Kotlin with Jetpack Compose
- MVVM architecture foundation
- All required dependencies configured
- Theme system with dark/light mode
- Basic package structure (ui, data, domain, utils)
- Git repository initialized

## Stage 1 Completion

Stage 1 (Data Layer & Calculations) has been completed. The project now has:
- Room database with RetrogradePeriodEntity and RetrogradePeriodDao
- Repository pattern implementation (RetrogradeRepositoryImpl)
- Database provider with initialization system
- Pre-calculated retrograde periods for all 8 planets (2024-2030)
- Educational content system (PlanetInfoProvider) with explanations for each planet
- RetrogradeCalculator utility updated to use repository
- Application class for database initialization
- Type converters for LocalDate support

## Stage 2 Completion

Stage 2 (Core UI - Home Dashboard) has been completed. The project now has:
- HomeScreen composable with gradient background (blue/green/purple theme)
- HomeViewModel with StateFlow-based state management
- PlanetTile component displaying planet status, retrograde indicator, and days remaining
- Navigation Component with NavGraph and route definitions
- TopAppBar with menu button
- Loading states and error handling
- Visual status indicators (red for active retrograde, teal for direct motion)
- Single-column layout displaying all 8 planets

