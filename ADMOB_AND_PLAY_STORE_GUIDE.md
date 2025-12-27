# AdMob & Google Play Store Setup Guide

## Part 1: AdMob Setup (While Waiting for Account Approval)

### Current Status
- ✅ AdMob SDK already integrated in the app
- ✅ Test ad unit ID configured (currently using Google's test ID)
- ⏳ Waiting for AdMob account approval (typically 1-3 days)

### What to Do While Waiting

1. **Prepare Your App Information**
   - App name: "Retro Now"
   - App category: Education or Lifestyle
   - Brief description of what your app does
   - Privacy policy URL (you'll need to create one)

2. **Create a Privacy Policy**
   - Required for AdMob and Google Play
   - Must mention:
     - Data collection (AdMob collects some data)
     - Third-party services (AdMob/Google)
     - User rights (GDPR if applicable)
   - Options:
     - Use a privacy policy generator (many free ones online)
     - Host on a simple website or GitHub Pages
     - Include in-app (less common but acceptable)

3. **Plan Your Ad Placement**
   - Currently: Banner ad at bottom of home screen ✅
   - Consider: This is a good, non-intrusive placement
   - Educational focus maintained ✅

### Once AdMob Account is Approved

1. **Get Your AdMob App ID**
   - Log into AdMob: https://admob.google.com
   - Go to Apps → Add App
   - Select "Android" → Enter app name
   - Copy the App ID (format: `ca-app-pub-XXXXXXXXXXXXXXXX~XXXXXXXXXX`)

2. **Create Banner Ad Unit**
   - In your app, go to Ad units → Add ad unit
   - Select "Banner"
   - Name it (e.g., "Home Screen Banner")
   - Copy the Ad Unit ID (format: `ca-app-pub-XXXXXXXXXXXXXXXX/XXXXXXXXXX`)

3. **Update Your App Code**
   
   **File: `app/src/main/AndroidManifest.xml`**
   ```xml
   <!-- Replace the test App ID with your actual AdMob App ID -->
   <meta-data
       android:name="com.google.android.gms.ads.APPLICATION_ID"
       android:value="ca-app-pub-YOUR-APP-ID-HERE~XXXXXXXXXX"/>
   ```
   
   **File: `app/src/main/java/com/retronow/app/ui/ads/BannerAd.kt`**
   ```kotlin
   // Replace the test ad unit ID with your actual banner ad unit ID
   adUnitId: String = "ca-app-pub-YOUR-AD-UNIT-ID-HERE/XXXXXXXXXX"
   ```

4. **Test with Real Ads**
   - Build and test the app
   - Verify ads load correctly
   - Check that ads don't interfere with app functionality

---

## Part 2: Google Play Store Preparation

### Pre-Launch Checklist

#### 1. App Information & Assets

**Required:**
- [ ] App name (max 50 characters): "Retro Now"
- [ ] Short description (max 80 characters)
- [ ] Full description (max 4000 characters)
- [ ] App icon (512x512 PNG, 32-bit)
- [ ] Feature graphic (1024x500 PNG)
- [ ] Screenshots (at least 2, up to 8):
  - Phone screenshots (required)
  - Tablet screenshots (optional but recommended)
  - 7-inch tablet: min 320px, max 3840px
  - 10-inch tablet: min 320px, max 3840px

**Recommended:**
- [ ] Promo video (YouTube link, optional)
- [ ] Promo graphic (optional)

#### 2. Content Rating

- [ ] Complete content rating questionnaire
- [ ] Answer questions about app content
- [ ] Get rating certificate (usually automatic)
- **Expected Rating:** Everyone or Teen (depending on content)

#### 3. Privacy & Security

- [ ] Privacy policy URL (required for AdMob apps)
- [ ] Data safety section:
  - Declare data collection practices
  - AdMob collects some data automatically
  - Be transparent about what data is collected
- [ ] Target audience and content
- [ ] Data encryption (if handling sensitive data)

#### 4. App Signing

- [ ] Create a keystore for app signing
- [ ] Generate signed APK/AAB
- [ ] Enable Play App Signing (recommended)
- [ ] Keep keystore file secure and backed up!

**Important:** Never lose your keystore file! You cannot update your app without it.

#### 5. Testing & Quality

**Before Publishing:**
- [ ] Test on multiple devices (different screen sizes)
- [ ] Test on different Android versions (8.0+)
- [ ] Test screen rotation (already configured ✅)
- [ ] Test with and without internet connection
- [ ] Test notification functionality
- [ ] Test all navigation flows
- [ ] Check for crashes or ANRs (Application Not Responding)
- [ ] Verify ads load correctly (once AdMob approved)
- [ ] Test performance (app should load quickly)
- [ ] Verify battery usage is reasonable

**Recommended:**
- [ ] Internal testing track (test with a few users)
- [ ] Closed testing track (wider beta testing)
- [ ] Open testing track (public beta, optional)

#### 6. Store Listing Details

**App Description Template:**
```
Retro Now - Track Planetary Retrograde Periods

Retro Now is an educational app that helps you track and understand planetary retrograde periods. Stay informed about current retrograde status for all major planets including Mercury, Venus, Mars, Jupiter, Saturn, Uranus, Neptune, and Pluto.

Features:
• Real-time retrograde status for all planets
• Detailed information about each planet's astrological significance
• Calendar view showing retrograde periods
• Educational content about retrograde motion
• Optional notifications for retrograde periods
• Beautiful, astronomy-inspired design

This app is for educational and informational purposes only. Retrograde periods are real astronomical phenomena, but astrological interpretations are matters of personal belief.

Privacy-focused: Works offline, minimal data collection.
```

**Keywords for ASO (App Store Optimization):**
- retrograde
- astrology
- astronomy
- planets
- mercury retrograde
- planetary motion
- educational

#### 7. Pricing & Distribution

- [ ] Set app as Free
- [ ] Select countries for distribution (or "All countries")
- [ ] Set up in-app purchases (if adding ad-free option later)

#### 8. Release Management

**Recommended Release Strategy:**
1. **Internal Testing** → Test with yourself/team
2. **Closed Testing** → Small group of beta testers
3. **Open Testing** (optional) → Public beta
4. **Production** → Full release

**Rollout Options:**
- Gradual rollout (start with 20% of users)
- Full rollout (all users at once)

---

## Part 3: Testing & Refinement Checklist

### Functional Testing

- [ ] **Home Screen**
  - [ ] All planets display correctly
  - [ ] Filter toggle works (show only retrograde)
  - [ ] Planet tiles auto-size correctly
  - [ ] Navigation menu works
  - [ ] Banner ad displays (once AdMob approved)
  - [ ] Screen rotation works smoothly

- [ ] **Planet Detail Screen**
  - [ ] All planets load correctly
  - [ ] Educational content displays properly
  - [ ] Retrograde periods list correctly
  - [ ] Cards auto-size to content
  - [ ] Background image displays
  - [ ] Navigation works

- [ ] **Calendar Screen**
  - [ ] Month navigation works
  - [ ] Retrograde periods display correctly
  - [ ] Color coding works
  - [ ] Today button works
  - [ ] Background image displays

- [ ] **Learn Screen**
  - [ ] Links open in browser
  - [ ] Content displays correctly

- [ ] **Settings Screen**
  - [ ] Theme toggle works
  - [ ] Notification settings work
  - [ ] Preferences persist

- [ ] **Notifications**
  - [ ] Notifications schedule correctly
  - [ ] Notifications display at correct times
  - [ ] Deep links work (tap notification → opens app)

### Performance Testing

- [ ] App launches in < 2 seconds
- [ ] No lag when scrolling
- [ ] Smooth transitions between screens
- [ ] No memory leaks
- [ ] Battery usage is reasonable
- [ ] Works on low-end devices (if targeting)

### Device Testing

Test on:
- [ ] Small phone (if available)
- [ ] Large phone
- [ ] Tablet (if supporting)
- [ ] Android 8.0 (minimum SDK)
- [ ] Latest Android version
- [ ] Different screen densities

### Edge Cases

- [ ] App behavior when no internet (should work offline)
- [ ] App behavior when database is empty
- [ ] App behavior when no planets in retrograde
- [ ] App behavior when rotating during loading
- [ ] App behavior when backgrounded/foregrounded
- [ ] Notification behavior when app is closed

---

## Part 4: Common Issues & Solutions

### AdMob Issues

**Ads not showing:**
- Check internet connection
- Verify ad unit IDs are correct
- Check AdMob account status (must be approved)
- Check logs for error messages
- Ensure test ads work first

**App rejected due to ads:**
- Make sure you're not showing ads before AdMob approval
- Use test ad IDs during development
- Ensure privacy policy mentions AdMob

### Google Play Issues

**App rejected:**
- Check content rating matches app content
- Ensure privacy policy is accessible
- Verify all required information is provided
- Check for policy violations

**Build issues:**
- Ensure you're using release build
- Check that app is signed correctly
- Verify target SDK requirements are met

---

## Part 5: Timeline Recommendation

### Week 1: Testing & Refinement
- Complete all functional testing
- Fix any bugs found
- Refine UI/UX based on testing
- Prepare screenshots and assets

### Week 2: AdMob Setup (Once Approved)
- Get AdMob App ID and Ad Unit ID
- Update code with real IDs
- Test ads thoroughly
- Verify privacy policy is ready

### Week 3: Play Store Preparation
- Create store listing
- Prepare all required assets
- Complete content rating
- Set up app signing
- Upload to internal testing

### Week 4: Beta Testing & Launch
- Internal testing
- Closed beta (optional)
- Address feedback
- Gradual rollout to production

---

## Quick Reference: File Locations

### AdMob Configuration
- **App ID**: `app/src/main/AndroidManifest.xml` (line ~20)
- **Ad Unit ID**: `app/src/main/java/com/retronow/app/ui/ads/BannerAd.kt` (line ~18)

### App Information
- **App Name**: `app/src/main/res/values/strings.xml`
- **Version**: `app/build.gradle.kts` (versionCode, versionName)
- **Package Name**: `app/build.gradle.kts` (applicationId)

### Signing
- Keystore file: Keep secure! Store in safe location.
- Signing config: `app/build.gradle.kts` (buildTypes → release)

---

## Important Notes

1. **Never lose your keystore file** - You cannot update your app without it
2. **Test thoroughly** - First impressions matter on Play Store
3. **Privacy policy is mandatory** - Required for AdMob and Play Store
4. **Start with internal testing** - Don't go straight to production
5. **Monitor reviews** - Respond to user feedback
6. **Keep dependencies updated** - For security and compatibility

---

## Resources

- **AdMob Help**: https://support.google.com/admob
- **Play Console Help**: https://support.google.com/googleplay/android-developer
- **Privacy Policy Generator**: https://www.freeprivacypolicy.com/ (one of many options)
- **App Icon Generator**: https://www.appicon.co/ (or similar tools)

---

**Last Updated**: 2024-12-19

