# Retro-Now: Iterative Development Plan

## Overview
This document outlines the iterative development plan for the Retro-Now Android application - a lightweight retrograde status app focused on educational content about planetary retrogrades.

## Development Philosophy
- **Iterative Approach**: Build in stages, test at each stage, get approval before proceeding
- **Simplicity First**: Prioritize core functionality, then enhance
- **Performance Focus**: Battery-efficient, fast, offline-capable
- **Documentation**: Update README, plan, and overview after each stage

---

## Stage 0: Project Setup & Foundation
**Status**: ✅ Completed  
**Goal**: Set up the Android project structure, dependencies, and basic configuration

### Tasks:
1. **Initialize Android Project**
   - Create new Android project with Kotlin
   - Set minimum SDK to API 26 (Android 8.0)
   - Configure Jetpack Compose
   - Set up MVVM architecture structure

2. **Project Configuration**
   - Configure build.gradle files
   - Add required dependencies:
     - Jetpack Compose
     - Room Database
     - Coroutines & Flow
     - ViewModel & LiveData
     - Navigation Component
     - Material Design 3
     - WorkManager (for notifications)

3. **Project Structure**
   - Create package structure:
     - `ui/` (screens, components)
     - `data/` (database, repositories)
     - `domain/` (models, use cases)
     - `utils/` (calculations, helpers)

4. **Git Setup**
   - Initialize git repository
   - Create `.gitignore` (exclude credentials, prompt.md)
   - Initial commit

5. **Documentation**
   - Create/update README.md
   - Create PROJECT_OVERVIEW.md
   - Document project structure

**Deliverables**:
- Working Android project skeleton
- All dependencies configured
- Git repository initialized
- Basic documentation

**Estimated Time**: 2-3 hours

---

## Stage 1: Data Layer & Calculations
**Status**: ✅ Completed  
**Goal**: Implement retrograde calculation logic and data storage

### Tasks:
1. **Investigate Rust CLI Tool**
   - Review `C:\Users\micha\Rust\CLI_Astro_Calc`
   - Determine integration approach (JNI, CLI calls, or rewrite logic)
   - If integration is complex, proceed with alternative

2. **Retrograde Calculation Implementation**
   - Implement or integrate retrograde calculation logic
   - Calculate retrograde periods for major planets (2024-2030)
   - Validate calculations against known retrograde dates

3. **Data Models**
   - Create Planet data class
   - Create RetrogradePeriod data class
   - Create database entities (Room)

4. **Database Setup**
   - Set up Room database
   - Create DAOs for retrograde data
   - Create repository pattern
   - Seed initial data (pre-calculated retrograde periods)

5. **Educational Content**
   - Create data models for educational content
   - Add initial educational text for each planet
   - Store in app resources (strings.xml or JSON)

**Deliverables**:
- Working retrograde calculation system
- Room database with seeded data
- Repository layer complete
- Educational content structure

**Estimated Time**: 4-6 hours

---

## Stage 2: Core UI - Home Dashboard
**Status**: Not Started  
**Goal**: Build the main home screen with planet status tiles

### Tasks:
1. **Design System Setup**
   - Define color scheme (blue/green/purple)
   - Set up theme (light/dark mode)
   - Create reusable UI components

2. **Home Screen Layout**
   - Implement background image (from Pictures folder)
   - Create planet tile component
   - Display all 8 planets in single column
   - Show retrograde status (active/inactive)
   - Show days remaining (if in retrograde)

3. **Navigation Setup**
   - Set up Navigation Component
   - Add menu button to home screen
   - Create navigation graph

4. **ViewModel for Home**
   - Create HomeViewModel
   - Connect to repository
   - Implement state management with Flow/LiveData

5. **Status Indicators**
   - Visual indicators for retrograde status
   - Date formatting and display
   - Loading states

**Deliverables**:
- Functional home dashboard
- Navigation structure
- Planet status display
- Theme support

**Estimated Time**: 4-5 hours

---

## Stage 3: Planet Detail Screen
**Status**: Not Started  
**Goal**: Create detailed view for individual planets

### Tasks:
1. **Planet Detail UI**
   - Create screen layout with background image
   - Implement dropdown/selector for planet selection
   - Display planet information

2. **Content Display**
   - Show current retrograde status
   - Display entry/exit dates
   - Show educational content about planet's retrograde
   - Format text for readability

3. **Planet Detail ViewModel**
   - Create ViewModel for planet detail
   - Handle planet selection
   - Load planet-specific data

4. **Navigation Integration**
   - Connect to navigation graph
   - Handle navigation from home screen

**Deliverables**:
- Planet detail screen functional
- Educational content display
- Planet selector working
- Navigation integrated

**Estimated Time**: 3-4 hours

---

## Stage 4: Calendar View
**Status**: Not Started  
**Goal**: Implement calendar showing retrograde periods

### Tasks:
1. **Calendar UI**
   - Create month view layout
   - Implement scrolling between months
   - Display day numbers

2. **Retrograde Visualization**
   - Draw colored lines for retrograde periods
   - Different colors for each planet
   - Show overlapping periods clearly

3. **Calendar Logic**
   - Month navigation (previous/next)
   - Calculate which days are in retrograde
   - Handle month boundaries

4. **Calendar ViewModel**
   - Create ViewModel for calendar
   - Load retrograde data for date range
   - Format data for display

5. **Background Image**
   - Apply background image from Pictures folder

**Deliverables**:
- Functional calendar view
- Retrograde periods visualized
   - Month navigation
   - Color-coded planet retrogrades

**Estimated Time**: 5-6 hours

---

## Stage 5: Learn & Settings Screens
**Status**: Not Started  
**Goal**: Complete remaining screens

### Tasks:
1. **Learn Screen**
   - Create layout with background image
   - Add links to educational resources
   - Style links appropriately
   - Add basic explanation of retrograde

2. **Settings Screen**
   - Create simple settings layout
   - Theme toggle (light/dark mode)
   - About section
   - Placeholder for notification settings

3. **Navigation Completion**
   - Connect all screens in navigation graph
   - Test navigation flow

**Deliverables**:
- Learn screen with resources
- Settings screen functional
- Complete navigation

**Estimated Time**: 2-3 hours

---

## Stage 6: Notifications (Optional Feature)
**Status**: Not Started  
**Goal**: Implement optional notification system

### Tasks:
1. **Notification Setup**
   - Configure notification channels
   - Request notification permissions
   - Set up WorkManager for scheduling

2. **Notification Logic**
   - Calculate notification times (1 day before, 3 days before, day of)
   - Schedule entry/exit notifications
   - Per-planet notification preferences

3. **Settings Integration**
   - Add notification toggle in settings
   - Per-planet notification controls
   - Notification timing preferences

4. **Testing**
   - Test notification delivery
   - Test notification cancellation
   - Handle edge cases

**Deliverables**:
- Working notification system
- User controls in settings
- Scheduled notifications

**Estimated Time**: 4-5 hours

---

## Stage 7: Polish & Optimization
**Status**: Not Started  
**Goal**: Optimize performance and polish UI

### Tasks:
1. **Performance Optimization**
   - Profile app performance
   - Optimize database queries
   - Implement lazy loading where needed
   - Reduce battery usage

2. **UI/UX Polish**
   - Refine animations (minimal)
   - Improve accessibility (screen readers, text sizes)
   - Test on different screen sizes
   - Test orientation changes

3. **Offline Functionality**
   - Ensure all data works offline
   - Test offline scenarios
   - Cache educational content

4. **Error Handling**
   - Add error states
   - Handle edge cases gracefully
   - User-friendly error messages

**Deliverables**:
- Optimized app performance
- Polished UI/UX
- Full offline support
- Robust error handling

**Estimated Time**: 4-6 hours

---

## Stage 8: Testing & Quality Assurance
**Status**: Not Started  
**Goal**: Comprehensive testing before release

### Tasks:
1. **Unit Tests**
   - Test calculation logic
   - Test ViewModels
   - Test repository layer

2. **UI Tests**
   - Test critical user flows
   - Test navigation
   - Test screen interactions

3. **Device Testing**
   - Test on various Android versions (8.0+)
   - Test on different screen sizes
   - Test on tablets
   - Performance testing on mid-range devices

4. **Beta Testing**
   - Prepare beta release
   - Gather feedback
   - Fix critical issues

**Deliverables**:
- Comprehensive test coverage
- Tested on multiple devices
- Beta feedback incorporated

**Estimated Time**: 6-8 hours

---

## Stage 9: Release Preparation
**Status**: Not Started  
**Goal**: Prepare for Google Play Store release

### Tasks:
1. **App Store Assets**
   - Create app icon
   - Create screenshots
   - Write app description
   - Create privacy policy

2. **Final Checks**
   - Code review
   - Security audit
   - Performance validation
   - Battery usage validation

3. **Documentation**
   - Final README update
   - User guide (if needed)
   - Developer notes

4. **Release Build**
   - Create release build
   - Sign APK/AAB
   - Prepare for Play Store upload

**Deliverables**:
- Release-ready app
   - All store assets
   - Final documentation

**Estimated Time**: 3-4 hours

---

## Future Enhancements (Post-Launch)
- Widget support for home screen
- Moon phases display
- Other astronomical events
- User journal/notes feature
- Multi-language support

---

## Notes
- Each stage should be completed and tested before moving to the next
- Ask for approval before proceeding to next stage
- Update README.md, ITERATIVE_PLAN.md, and PROJECT_OVERVIEW.md after each stage
- Keep commits atomic and well-documented
- Focus on simplicity and performance throughout

---

## Current Status
**Current Stage**: Stage 1 - Data Layer & Calculations ✅ Completed  
**Next Stage**: Stage 2 - Core UI - Home Dashboard  
**Last Updated**: 2024-12-19

### Stage 0 Completion Summary
- ✅ Android project initialized with Kotlin
- ✅ Minimum SDK set to API 26 (Android 8.0)
- ✅ Jetpack Compose configured
- ✅ MVVM architecture structure created
- ✅ All dependencies added (Compose, Room, Navigation, WorkManager, etc.)
- ✅ Package structure created (ui/, data/, domain/, utils/)
- ✅ Basic MainActivity with Compose setup
- ✅ Theme system with dark/light mode support
- ✅ Git repository initialized with initial commit
- ✅ Documentation files created

### Stage 1 Completion Summary
- ✅ Rust CLI tool investigated (integration deemed complex, using pre-calculated data approach)
- ✅ Room database entities and DAOs created (RetrogradePeriodEntity, RetrogradePeriodDao)
- ✅ Repository pattern implemented (RetrogradeRepositoryImpl)
- ✅ Database provider and initialization system created
- ✅ Pre-calculated retrograde data seeded (2024-2030 for all 8 planets)
- ✅ Educational content structure created (PlanetInfoProvider with explanations for each planet)
- ✅ RetrogradeCalculator updated to use repository
- ✅ Application class created for database initialization
- ✅ Type converters for LocalDate added

