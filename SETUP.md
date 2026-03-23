# Setup & Build Guide

## Requirements

- **Android Studio** - Latest version (Koala or newer)
- **JDK** - Version 11 or higher
- **Android SDK** - API level 24-35
- **Gradle** - 8.8.0+ (included via Gradle Wrapper)

## Build Commands

```bash
# Build debug APK
./gradlew build

# Build and install to emulator/device
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/metmuseum2/
│   │   ├── MainActivity.kt              # Main UI entry point
│   │   ├── viewmodel/MainViewModel.kt   # State & business logic
│   │   ├── service/                     # Retrofit API setup
│   │   ├── model/                       # Data classes
│   │   └── ui/theme/                    # Compose theme styling
│   └── res/                             # Resources & icons
└── build.gradle.kts                     # App configuration
```

## Development

Open in Android Studio and select "Open Existing Project" to load the workspace. The Gradle sync will download all dependencies automatically.
