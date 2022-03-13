SatelliteApp Code Challenge
------------
Android app that showcases architecture and libraries used by me.
There are two simple pages to show basic implementations followed.
Satellite Search page to show and search Satellites that saved in RoomDatabase provided By Json files in asset folder and Satellite Detail Page to show extra information about Satellites.

Architecture
------------
Single Activity
MVVM Pattern
View: Renders UI and delegates user actions to ViewModel
ViewModel: Can have simple UI logic but most of the time just gets the data from Repository
Repository: Single source of data. Responsible to get data from Room Databases

Tech Stack
------------
Navigation Component: Consistent navigation between views
LiveData: Lifecycle aware observable and data holder
ViewModel: Holds UI data across configuration changes
Databinding: Binds UI components in layouts to data sources
Hilt: Dependency injector
Coroutines: Asynchronous programming
GSON: JSON serializer/deserializer
JUNIT5: unit testing
Mockito: mock objects for unit testing

NOTE :
I have an issue for saving position.json in database because of my customTypeConverter does not 
work properly, I write my code and you can see them but for updating Ui interval, I update that text
with currentTime

