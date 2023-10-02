# Klarna
Klarna test task to show weather

The main goal of this app is to be a sample of how to build an high quality Android application that uses the mutlimodule Architecture components,MVVM,Coroutines,Jetpack,Koin etc. in Kotlin.

## Development Approach
Used the Clean Architecture approach to achieve the 
- Reusability
- Scalability
- Testability
- Build time

## Architecture
- The app is built with a structure called MVVM Clean architecture, which helps in organizing and managing data flow, separating concerns, making it easier to test, and promoting reusability.
- Used Koin for dependency injection, which is a way of providing the necessary components
- Retrofit is employed for integrating with APIs and retrieve data.
- The app is divided into feature modules to handling the current weather: App, Common, Domain, Feature in easy word we can say I used Clean Architecture
- The outer domain module has sub-modules specifically designed for the network layer. This separation of concerns helps in keeping the codebase organized and manageable.
- Within the feature domain layer, there's an implementation of a use case. 
- ViewModels take on the responsibility of communicating with the various data layers. They act as intermediaries between the user interface (View) and the data sources.

## UI
Application Design is based on Jetpack Compose.

Application have a single screen to show Current Weather.
It consist of three states.

- Loading State. 
- Error State. 
- Weather Loaded. 

## Thirt part API
<li><a href="https://api.open-meteo.com/v1/forecast?latitude=52.507929&longitude=13.400848&current_weather=true">Current Weather API</a></li>


## Libraries used for feature development

<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a></li>
<li><a href="https://insert-koin.io/">Koin</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://developer.android.com/jetpack/compose">Jetpack Compose</a></li>


## Testing Strategies
Unit testing is done using Junit.
<li><a href="https://github.com/junit-team/junit4">Junit</a></li>
