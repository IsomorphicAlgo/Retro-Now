# Introduction
I am making a retrograde app for Android. This app will be a lightweight application that displays the status of planets and their retrograde status. It will also describe the status of other astrological bodies. It will also serve as a basic resource for learning what these topics mean and how they have been known to affect some individuals.

# Important Things to Remember
- I'm not making a horoscope app. This will focus on retrograde status as the primary component.
- I would like for this to work on as many devices as possible, so simplicity will be key.
- The app should be educational and informative, not predictive.
- Performance and battery efficiency are priorities.
- The UI should be clean, modern, and easy to understand at a glance.

# Requirements
- Make an interative plan to produce this application
- Ask before proceeding with future stages. 
- Update the readme, plan, and overview every stage.
- credentials files, this prompt file, 

## Core Features
- Display current retrograde status for all major planets (Mercury, Venus, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto)
- Show when each planet enters and exits retrograde
- Provide educational content explaining what retrograde means for each planet
- Include a calendar view showing upcoming retrograde periods
- Send optional notifications when planets enter/exit retrograde

## User Experience
- App should work offline after initial data is cached
- Minimal data usage
- Fast load times (under 2 seconds on mid-range devices)
- Support Android 8.0 (API 26) and above for maximum compatibility
- Dark mode and light mode support
- Accessibility features (screen reader support, adjustable text sizes)

## Content Requirements
- Brief explanation of what "retrograde" means in astronomy
- Description of each planet's retrograde and its traditional astrological interpretations
- Historical context about retrograde observations
- Clear disclaimer that content is for informational/entertainment purposes

## Key Screens
1. **Home/Dashboard**: Current retrograde status at a glance
2. **Planet Detail**: In-depth info about specific planet's retrograde
3. **Calendar View**: Timeline of past and future retrograde periods
4. **Learn**: Educational resources about retrogrades
5. **Settings**: Notifications, theme preferences, about section

## Design
- the ui will have a blue / green / purple design with pictures as backgrounds for each key screen that I will supply.
- the home dashboard will have one column of tiles for each planet, with a menu button for navigation to other pages.
- The Planet Detail will be a screen of text with a designed drop down menu for th eindividual to select the planet/celestial body.
- the Calendar View page will have a scrolling page with a month view at a time. Each day will be a simple number and periods of retrograde will be portrayed by a colored line crossing the days for each planet. 
- the learn page will be a page of links to resources
- settings page can be super simple  
- the folder 'C:\Users\micha\OneDrive\Desktop\Retro-Now\Pictures' contains background pictures for 

## Visual Style
- Clean, minimalist interface with astronomy-inspired aesthetics
- Color scheme: Deep space blues/purples with bright accent colors for active retrogrades
- Planet icons should be recognizable and visually distinct
- Use of card-based layout for easy scanning

## UI Components
- Status indicators (active/inactive retrograde)
- Date counters (days remaining in retrograde)


## Data Management
- Calculate retrograde periods using astronomical ephemeris data
- Store retrograde schedules locally (SQLite or Room database)
- Update data periodically (monthly or quarterly) when online
- Cache all educational content for offline access

## Notifications (Optional)
- User can opt-in to notifications
- Alert when planet enters retrograde (1 day before, 3 days before, day of)
- Alert when planet exits retrograde
- Customizable per planet (e.g., only notify for Mercury retrograde)

## Calculations
- Use established astronomical libraries or APIs for accurate planetary positions
- you can use my CLI Asto Calc written in rust, found here: C:\Users\micha\Rust\CLI_Astro_Calc, if it's not too difficult. 
- If the above doesnt work, Consider using Swiss Ephemeris or similar reliable data source
- Show retrograde periods accurate to the day

# Frontend

## Technology Stack
- **Language**: Kotlin (preferred) or Java
- **UI Framework**: Jetpack Compose (modern, recommended) or XML layouts
- **Architecture**: MVVM (Model-View-ViewModel) pattern
- **Navigation**: Jetpack Navigation Component

## Key Libraries to Consider
- Jetpack Compose for UI (if using modern approach)
- Room for local database
- Coroutines for async operations
- ViewModel and LiveData/Flow for state management
- Material Design 3 components
- WorkManager for periodic data updates

## Responsive Design
- Support both phone and tablet layouts
- Adaptive layouts for different screen sizes
- Handle orientation changes gracefully

# Backend

## Data Sources
- you can use my CLI Asto Calc written in rust, found here: C:\Users\micha\Rust\CLI_Astro_Calc, if it's not too difficult. 
- Use astronomical APIs or libraries for ephemeris data (e.g., Swiss Ephemeris, NASA APIs, or astronomy calculation libraries)
- Consider pre-calculating retrograde periods for next 5-10 years and bundling with app
- Minimal backend needed since data is relatively static and predictable

## API Requirements (if using external service)
- Endpoint to fetch retrograde schedules
- Endpoint to fetch educational content updates
- Low frequency of calls (weekly or monthly updates maximum)
- Fallback to local data if API unavailable

## Alternative: No Backend
- Bundle pre-calculated retrograde data with the app
- Include all educational content in app resources
- Update data through app updates (simpler, more reliable, works offline)

# Infrastructure

## Hosting (if needed)
- if needed I have a server at home.
- otherwise lets explore cloud options. 

## Distribution
- Google Play Store as primary distribution

## Analytics (Optional)
- Privacy-focused analytics (Firebase Analytics or similar)
- Track feature usage, crashes, performance metrics
- No personal data collection
- Comply with GDPR and other privacy regulations

## Maintenance
- Plan for semi-annually updates with new retrograde calculations
- Monitor crash reports and user feedback
- Keep dependencies updated for security

# Technical Considerations

## Performance Optimization
- Lazy loading for content
- Image optimization (use vector drawables where possible)
- Minimal animations to reduce battery drain
- Efficient database queries

## Testing Strategy
- Unit tests for calculation logic
- UI tests for critical user flows
- Test on various Android versions and screen sizes
- Beta testing program before full release

## Future Enhancements (Optional)
- Widget support for home screen
- Moon phases display
- Other astronomical events
- User journal/notes feature
- Multi-language support