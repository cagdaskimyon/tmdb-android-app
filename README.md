# An Android sample project for Mobven
An Android sample project written in Kotlin using TheMovieDatabase API.

### Introduction
This is an Android app that uses TheMovieDatabase REST API, written in Kotlin with MVP pattern.

The app starts with a splash screen and lists movies in 3 tabs, Now Playing, Upcoming and Top Rated. The tabs support infinite scroll. Users can search movies on the main page and mark their favorites in the movie details section. Favorite movies are saved to a local database.

### Software
- Android Studio 3.4.1
- Android SDK
- JDK 1.8

### Third Party Libraries
- Retrofit 2.6.0
- Retrofit GSON 2.6.0
- OkHttp 4.0.1
- Glide 4.8.0
- Joda Time 2.10.1

### How to use
The project should be able to build in Android Studio without any additional setting. Please check software and libraries that are mentioned above.

### Notes
Since the whole project was completed in 4 days, all design and coding decisions was made on the fly. There is a lot of room for improvement. 

### Future Improvements
- UI changes (Color scheme, content placement, etc.)
- UX changes (App feedback, micro interactions, etc.)
- Presenting more information in movie details section
- Launching a ticket app for Now Playing and Upcoming movies if it is installed
