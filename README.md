# VideoCatalogue

I used MVVM & Kotlin to build a video catalogue for demo purpose. One benefit of using MVVM is the data state can survive after the configuration changes, so both the landscape mode and portrait mode can be supported without much effort. Thanks, ViewModel! Coroutines is used in this app to replace the typical RxJava approach. LiveData & ViewModel from architecture components is also applied in this app, it makes the MVVM implementation much easier, I also spent some time to refine the UI and style, and all related settings were extracted to xml files to achieve better maintainability. 

Regarding the unit testing, I wrote unit tests for repositories, and view models, and also used espresso for UI testing.

![screenshots](https://i.imgur.com/gf3gswc.png)

## Features

- Support both tablet & phone layout
- Support landscape & portrait orientation
- Have unit tests for ViewModels and repositories
- Have an end-to-end UI test.

## Tech Stack
- Programming language: Kotlin
- Architecture pattern: Model-View-ViewModel(MVVM)
- Asynchronous Programming: Kotlin Coroutines
- Dependency Injection: Dagger2
- Network requesting: Retrofit
- Android Architecture components: LiveData, ViewModel
- Unit testing: JUnit, Mockito
- UI testing: Espresso

