# NewsApp

**NewsApp** is a modern Android application built with Kotlin that fetches, displays, and manages news articles from remote APIs based on category and country, integrating local caching and offering search capabilities. The project leverages best practices in architecture and utilizes advanced libraries such as Dagger Hilt for dependency injection and Room for local database storage.

---

## Features

- **Fetch News by Category and Country:** Retrieve top headlines via remote API, filtered by category (e.g., general, sports, technology) and country.
- **Search News:** Users can search for news articles by keywords, category, and country.
- **Local Caching:** News are cached locally using Room to enable offline access and faster reloads.
- **Pull-to-Refresh:** Update headlines manually to fetch the latest news.
- **Details View:** Tap any headline to view complete news details, including images and full article content.
- **MVVM + Clean Architecture:** Separation of concerns using repository, use case, and presentation layers.
- **Dependency Injection:** Dagger Hilt is used throughout for robust and testable architecture.
- **Centralized Dependency Management:** All library versions are managed in one place using Gradle Version Catalogs (`libs.versions.toml`).
- **Multiple Flavors:** Build and run the app as either a Free version (limited categories, only US news) or a Pro version (all features and countries) from the same codebase.

---

## Architecture Overview

```
Domain Layer
  ↳ Repository interfaces, UseCases

Data Layer
  ↳ Remote API (Retrofit), Local database (Room), Data mapping, Repository implementations

Presentation Layer
  ↳ Fragments, ViewModels, UI States

DI Layer
  ↳ Dagger Hilt modules*

```

- **Repository Pattern:** `NewsRepository` interface abstracts data sources. `NewsRepositoryImpl` orchestrates local/remote data flows.
- **Use Cases:** Encapsulate business logic—fetch, refresh, and search news.
- **UI:** Fragments for home and detail screens. ViewModels manage state and business logic.

---

## Getting Started

### Prerequisites

- Android Studio Flamingo or above
- Kotlin 1.5+
- Gradle 8+
- API Key for the selected news provider (set in `BuildConfig.NEWS_API_KEY`)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/omerakpul/NewsAppXML.git
   ```
2. **Open in Android Studio**
3. **Add your API Key:**  
   Edit your `local.properties` or relevant gradle files to provide your news API key and base URL:
   ```
   NEWS_API_KEY=your_api_key_here
   NEWS_API_BASE_URL=https://newsapi.org/v2/
   ```

4. **Build & Run:**  
   Select your device/emulator and run the app.

---

## Usage

- **Home Screen:**  
  - Select country and category from spinners.
  - Browse headlines.
  - Pull down to refresh.
  - Search via the search bar.

- **Details Screen:**  
  - Tap a headline for details and image.
  - Go back to home screen.

---

## Core Classes

- **`NewsHomeFragment`**: Main UI for news listing, filtering, searching.
- **`NewsDetailsFragment`**: Displays details of selected news.
- **`NewsHomeViewModel`**: Manages fetching, refreshing, and searching news with UI state management.
- **`NewsRepositoryImpl`**: Handles local/remote data sources.
- **Room Entities & DAO**: For persistent local storage.
- **Dagger Hilt Modules**: AppModule, RepositoryModule, UseCaseModule for DI.
- **BaseFragment**: All fragments inherit from this class to share common UI logic and reduce code duplication.

---

## Technologies Used

- Kotlin
- Android Jetpack (ViewModel, LiveData, Room, Navigation)
- Retrofit
- Dagger Hilt
- Coil (image loading)
- MVVM + Clean Architecture

---

## Extending & Customizing

- Add more categories or countries by updating UI spinner lists and API calls.
- Implement bookmarking or sharing news.
- Add tests using JUnit and Mockito.

---

## License

This project does not specify a license. Please contact the owner for usage terms.

---

## Author

- [omerakpul](https://github.com/omerakpul)

---

## Troubleshooting

- Ensure your API key is correctly set.
- Ensure internet connectivity for fetching remote news.
- For database migration issues, the app uses destructive migration on Room.

---
