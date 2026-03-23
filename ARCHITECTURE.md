# Architecture Overview

## Pattern: MVVM with Coroutines

This project follows the Model-View-ViewModel pattern combined with reactive state management using Jetpack Compose and Kotlin Coroutines.

## Layer Breakdown

**View Layer (UI)**  
Jetpack Compose handles all UI rendering. `MainActivity` observes ViewModel state and recomposes on changes. No business logic in UI code.

**ViewModel Layer**  
`MainViewModel` manages coroutine scope, API coordination, and exposes mutable state through StateFlow and State lists. Acts as bridge between UI and data layers.

**Data Layer**  
`RetrofitClient` provides HTTP client singleton. `MetApiService` defines API endpoints. Models represent both API responses and app domain objects.

**API Integration**  
Retrofit deserializes JSON responses from the Metropolitan Museum API into Kotlin data classes via Gson converter.

## Data Flow

UI (Compose) → ViewModel (viewModelScope.launch) → Service (API Call) → Model (Data Classes) → ViewModel (Update State) → UI (Recompose)
