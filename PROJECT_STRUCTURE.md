# Retro-Now Project Structure

This document outlines the project structure created in Stage 0.

## Root Level
```
Retro-Now/
├── .gitignore              # Git ignore rules (excludes prompt.md, credentials)
├── build.gradle.kts        # Root build configuration
├── settings.gradle.kts     # Project settings
├── gradle.properties       # Gradle properties
├── README.md               # Project readme
├── ITERATIVE_PLAN.md       # Development plan
├── PROJECT_OVERVIEW.md     # Project overview
├── PROJECT_STRUCTURE.md    # This file
└── app/                    # Main app module
```

## App Module Structure
```
app/
├── build.gradle.kts                    # App-level build configuration
├── proguard-rules.pro                  # ProGuard rules
└── src/
    ├── main/
    │   ├── AndroidManifest.xml        # App manifest
    │   ├── java/com/retronow/app/
    │   │   ├── ui/                    # UI layer
    │   │   │   ├── MainActivity.kt    # Main activity
    │   │   │   └── theme/             # Theme components
    │   │   │       ├── Color.kt       # Color definitions
    │   │   │       ├── Theme.kt       # Theme setup
    │   │   │       └── Type.kt        # Typography
    │   │   ├── data/                  # Data layer
    │   │   │   ├── database/          # Room database
    │   │   │   │   └── RetrogradeDatabase.kt
    │   │   │   └── repository/        # Repositories
    │   │   │       └── RetrogradeRepository.kt
    │   │   ├── domain/                # Domain layer
    │   │   │   └── model/             # Domain models
    │   │   │       ├── Planet.kt
    │   │   │       └── RetrogradePeriod.kt
    │   │   └── utils/                 # Utilities
    │   │       └── RetrogradeCalculator.kt
    │   └── res/                        # Resources
    │       ├── values/
    │       │   ├── strings.xml
    │       │   └── themes.xml
    │       ├── xml/
    │       │   ├── backup_rules.xml
    │       │   └── data_extraction_rules.xml
    │       └── mipmap/                # App icons (placeholder)
    ├── test/                          # Unit tests
    └── androidTest/                  # Instrumented tests
```

## Package Structure

### UI Layer (`com.retronow.app.ui`)
- **MainActivity.kt**: Entry point of the app
- **theme/**: Theme configuration (colors, typography, dark/light mode)

### Data Layer (`com.retronow.app.data`)
- **database/**: Room database setup
- **repository/**: Repository interfaces (implementation in Stage 1)

### Domain Layer (`com.retronow.app.domain`)
- **model/**: Domain models (Planet, RetrogradePeriod)

### Utils (`com.retronow.app.utils`)
- **RetrogradeCalculator.kt**: Calculation utilities (implementation in Stage 1)

## Dependencies Configured

### Core
- Kotlin 1.9.20
- AndroidX Core KTX
- Lifecycle Runtime KTX

### UI
- Jetpack Compose BOM 2023.10.01
- Material Design 3
- Navigation Compose

### Architecture
- ViewModel Compose
- Lifecycle Runtime Compose

### Data
- Room Database 2.6.1
- Coroutines Android 1.7.3

### Other
- WorkManager 2.9.0 (for notifications)

## Build Configuration

- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 34
- **Compile SDK**: 34
- **Java Version**: 17
- **Kotlin JVM Target**: 17

## Architecture

The project follows **MVVM (Model-View-ViewModel)** architecture:

- **Model**: Domain models and data sources
- **View**: Jetpack Compose UI
- **ViewModel**: Business logic and state management

## Next Steps (Stage 1)

1. Implement retrograde calculation logic
2. Set up Room database entities and DAOs
3. Implement repository pattern
4. Seed initial retrograde data
5. Add educational content structure

---

**Last Updated**: 2024-12-19

