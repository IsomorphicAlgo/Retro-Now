# Testing Guide for Retro-Now

This guide will help you test the Retro-Now Android app on your laptop (using an emulator) and on your Android phones.

## Prerequisites

### For Laptop Testing (Android Emulator)

1. **Android Studio** - Download and install from [developer.android.com/studio](https://developer.android.com/studio)
2. **Android SDK** - Included with Android Studio
3. **Android Virtual Device (AVD)** - Create an emulator through Android Studio

### For Phone Testing

1. **USB Cable** - To connect your phone to your laptop
2. **USB Debugging Enabled** - On your Android phone
3. **Developer Options Enabled** - On your Android phone

---

## Part 1: Setting Up Android Studio and Emulator

### Step 1: Install Android Studio

1. Download Android Studio from the official website
2. Run the installer and follow the setup wizard
3. During installation, make sure to install:
   - Android SDK
   - Android SDK Platform
   - Android Virtual Device (AVD)

### Step 2: Create an Android Virtual Device (AVD)

1. Open Android Studio
2. Click **Tools** → **Device Manager** (or click the device manager icon)
3. Click **Create Device**
4. Select a device definition (e.g., Pixel 5)
5. Click **Next**
6. Select a system image:
   - Choose **API 26** (Android 8.0) or higher
   - Click **Download** if needed
   - Click **Next** after download completes
7. Configure AVD:
   - Name: "RetroNow_Test"
   - Click **Finish**

### Step 3: Open the Project

1. In Android Studio, click **File** → **Open**
2. Navigate to: `C:\Users\micha\OneDrive\Desktop\Retro-Now`
3. Click **OK**
4. Wait for Gradle sync to complete (this may take a few minutes on first open)

---

## Part 2: Building and Running on Emulator

### Step 1: Start the Emulator

1. In Android Studio, click the **Device Manager** icon
2. Find your AVD (RetroNow_Test)
3. Click the **Play** button (▶) next to it
4. Wait for the emulator to boot (this may take 1-2 minutes)

### Step 2: Build and Run

1. In Android Studio, click **Run** → **Run 'app'** (or press `Shift + F10`)
2. Select your emulator from the device list
3. Click **OK**
4. Wait for the app to build and install (first build may take 2-5 minutes)

### Step 3: Test the App

Once the app launches on the emulator, test the following:

#### Home Screen Tests:
- ✅ Verify all 8 planets are displayed in a single column
- ✅ Check that each planet shows its name
- ✅ Verify status indicators (red dot = retrograde, teal dot = direct)
- ✅ Check days remaining display for planets in retrograde
- ✅ Test scrolling through the planet list
- ✅ Verify menu button appears in top bar

#### Navigation Tests:
- ✅ Tap on a planet tile
- ✅ Verify navigation to planet detail screen
- ✅ Check back button works to return to home

#### Planet Detail Screen Tests:
- ✅ Verify planet name appears in top bar
- ✅ Check current status section shows correct retrograde state
- ✅ Verify educational content is displayed
- ✅ Check upcoming retrograde periods are listed
- ✅ Check past retrograde periods are listed
- ✅ Test "Select" button to open planet selector
- ✅ Select different planets from the dialog
- ✅ Verify content updates when selecting different planets

#### Error Handling Tests:
- ✅ Turn off Wi-Fi/data on emulator
- ✅ Restart app (should work offline after initial load)
- ✅ Verify loading states appear during data fetch

---

## Part 3: Testing on Physical Android Phones

### Step 1: Enable Developer Options on Your Phone

1. Open **Settings** on your Android phone
2. Scroll to **About Phone** (or **About Device**)
3. Find **Build Number**
4. Tap **Build Number** 7 times
5. You'll see a message: "You are now a developer!"

### Step 2: Enable USB Debugging

1. Go back to **Settings**
2. Find **Developer Options** (usually under System or Advanced)
3. Enable **Developer Options** toggle
4. Enable **USB Debugging**
5. (Optional) Enable **Stay Awake** (keeps screen on while charging)

### Step 3: Connect Your Phone

1. Connect your phone to your laptop using a USB cable
2. On your phone, you may see a prompt: **Allow USB Debugging?**
3. Check **Always allow from this computer**
4. Tap **OK**

### Step 4: Verify Connection

1. In Android Studio, open the **Terminal** (bottom panel)
2. Type: `adb devices`
3. You should see your device listed (e.g., `ABC123XYZ    device`)

If your device doesn't appear:
- Try a different USB cable
- Try a different USB port
- Make sure USB Debugging is enabled
- On your phone, check the USB connection mode (should be "File Transfer" or "MTP")

### Step 5: Run on Your Phone

1. In Android Studio, click **Run** → **Run 'app'**
2. Select your physical device from the device list
3. Click **OK**
4. The app will build, install, and launch on your phone

### Step 6: Test on Multiple Phones

Repeat Steps 1-5 for each phone you want to test on.

---

## Part 4: Testing Checklist

Use this checklist to ensure all features work correctly:

### Home Screen
- [ ] All 8 planets visible
- [ ] Planet names correct
- [ ] Status indicators work (red/teal dots)
- [ ] Days remaining shows for retrograde planets
- [ ] Scrolling works smoothly
- [ ] Menu button visible and clickable
- [ ] Loading spinner appears on first load
- [ ] App works offline after initial load

### Navigation
- [ ] Tapping planet navigates to detail screen
- [ ] Back button returns to home
- [ ] Navigation is smooth (no lag)

### Planet Detail Screen
- [ ] Planet name displays correctly
- [ ] Current status section shows correct state
- [ ] Educational content is readable
- [ ] Upcoming periods listed correctly
- [ ] Past periods listed correctly
- [ ] Planet selector dialog opens
- [ ] Can select different planets
- [ ] Content updates when planet changes
- [ ] Back button works

### Performance
- [ ] App launches in under 2 seconds
- [ ] No lag when scrolling
- [ ] Smooth transitions between screens
- [ ] No crashes or freezes

### Different Screen Sizes
- [ ] Test on small phone (if available)
- [ ] Test on large phone (if available)
- [ ] Test on tablet (if available)
- [ ] Layout adapts correctly to screen size

### Different Android Versions
- [ ] Test on Android 8.0 (API 26) if possible
- [ ] Test on Android 9.0+ if available
- [ ] Test on latest Android version

---

## Part 5: Common Issues and Solutions

### Issue: "Gradle sync failed"
**Solution:**
- Check internet connection
- In Android Studio: **File** → **Invalidate Caches** → **Invalidate and Restart**
- Wait for Gradle to re-sync

### Issue: "Device not found" when connecting phone
**Solution:**
- Install phone drivers (usually automatic on Windows)
- Try different USB cable
- Enable USB Debugging on phone
- Check USB connection mode

### Issue: "App crashes on launch"
**Solution:**
- Check Logcat in Android Studio for error messages
- Make sure database initialized (check first launch)
- Try uninstalling and reinstalling the app

### Issue: "No planets showing"
**Solution:**
- Check if database was seeded (first launch takes a moment)
- Check Logcat for database errors
- Try restarting the app

### Issue: "Emulator is slow"
**Solution:**
- Allocate more RAM to emulator in AVD settings
- Enable hardware acceleration
- Use a physical device for better performance

---

## Part 6: Debugging Tips

### View Logs
1. In Android Studio, open **Logcat** (bottom panel)
2. Filter by your app: `package:mine` or search for "RetroNow"
3. Look for errors (red) or warnings (yellow)

### Check Database
The app uses Room database. To inspect:
1. Use **Device File Explorer** in Android Studio
2. Navigate to: `/data/data/com.retronow.app/databases/`
3. Find `retrograde_database.db`
4. You can pull this file and inspect with a SQLite browser

### Test Offline Mode
1. Turn off Wi-Fi/data on device
2. Restart app
3. Verify all data still loads (should work after initial seed)

---

## Part 7: Quick Test Commands

### Using ADB (Android Debug Bridge)

Open Terminal in Android Studio and use these commands:

```bash
# List connected devices
adb devices

# Install app directly
adb install app/build/outputs/apk/debug/app-debug.apk

# Uninstall app
adb uninstall com.retronow.app

# View app logs
adb logcat | grep RetroNow

# Clear app data
adb shell pm clear com.retronow.app
```

---

## Part 8: Performance Testing

### Measure Launch Time
1. Uninstall app
2. Install fresh
3. Time from tap to home screen display
4. Should be under 2 seconds

### Test Battery Usage
1. Use phone's battery settings
2. Monitor Retro-Now app usage
3. Should be minimal (app is lightweight)

### Test Memory Usage
1. In Android Studio: **View** → **Tool Windows** → **Profiler**
2. Run app
3. Monitor memory usage
4. Should be stable (no memory leaks)

---

## Summary

You now have everything you need to test Retro-Now on:
- ✅ Android Emulator (laptop)
- ✅ Physical Android phones

**Next Steps:**
1. Set up Android Studio and create an emulator
2. Build and run the app
3. Test all features using the checklist
4. Test on your physical phones
5. Report any issues you find

**Happy Testing! 🚀**

