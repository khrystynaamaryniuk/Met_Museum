# Met Museum Gallery

An Android application that fetches and displays artwork from the Metropolitan Museum of Art API with a modern Compose UI.

## Features

- **Browse Artworks** - Explore random artwork from the Met's collection
- **Rich Details** - View artwork title, artist, date, and high-resolution images
- **Modern UI** - Built with Jetpack Compose for smooth, responsive design
- **Real-time Loading** - Async data fetching with loading indicators
- **Image Optimization** - Efficient image loading using Coil

## Tech Stack

| Category | Technology |
|----------|-----------|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Architecture | MVVM + ViewModel |
| Networking | Retrofit 2 |
| Image Loading | Coil |
| API | Metropolitan Museum Open API |

## Getting Started

```bash
git clone https://github.com/khrystynaamaryniuk/Met_Museum.git
cd Met_Museum
./gradlew build
./gradlew installDebug
```

## How It Works

1. **Fetch IDs** - Request available artwork object IDs from the Met API
2. **Randomize** - Shuffle and select 50 random artworks
3. **Load Details** - Fetch individual artwork details and images asynchronously
4. **Display** - Render artwork in a scrollable Compose UI with full details

## Architecture

**MainActivity** - Entry point; composes UI using Compose framework  
**MainViewModel** - Manages state and coordinates API calls via coroutines  
**RetrofitClient** - Singleton for HTTP requests to Metropolitan Museum API  
**MetApiService** - Interface defining API endpoints  
**Models** - Data classes (Artwork, ArtworkDetailResponse, ObjectIDsResponse)

## Dependencies

- `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle management
- `androidx.activity:activity-compose` - Compose integration
- `androidx.compose.material3` - Material Design components
- `io.coil:coil-compose` - Image loading
- `com.squareup.retrofit2:retrofit` - HTTP client
- `com.squareup.retrofit2:converter-gson` - JSON parsing
