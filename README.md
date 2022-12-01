# Tending-Repos


[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

## Preview 

### Light Mode 
<img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/Screenshot_20221201_184258.png"  width="300" height="622" /> <img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/Screenshot_20221201_183817.png"   width="300" height="622" /><img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/Screenshot_20221201_184237.png"   width="300" height="622" />

### Dark Mode 

<img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/Screenshot_20221201_184044.png"   width="300" height="622" /><img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/Screenshot_20221201_184052.png"  width="300" height="622" /><img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/Screenshot_20221201_184201.png"  width="300" height="622" />




## The Challenge
- Please complete using:
- Android using Kotlin as the development language and supporting min API level 21
(Lollipop)
- Use Git as you would on a normal production project to show code history and
development
- The app should fetch the trending repositories from the provided public API and
display it to the users
- Assume API calls have a cost per call and that in general slightly stale data is ok -
but users should be able to see current data when required.
- Shimmer animation on the table cell while api is fetching
- Dark mode support (for bonus points)


## Project Overview

* Fetch trending repos list from Github search API.
* Cache data offline for to decrease network usage. 
* Enable the user to force fetch from network.
* Enable Dark mode for the user's convenience.
* Cache layer will always be our source of truth for data


## Project Architecture 
The project is following [clean architucte](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) , It’s a group of practices and decisions that makes the code testable with independable components.

The following diagram shows how all the modules will interact with one another.

<img src="https://github.com/MohNage7/Tending-Repos/blob/develop/screenshots/clean_architecture_mvvm.png"  width=772 height=772  />


### Dependency Flow
As illustrated in the image above each component depends only on the component below it. the higher layers will request the needed data from the layers below it
and the data is being provided by the lower layers by a reactive paradigm. 

#### Dependency Injection 
Allows classes to define their dependencies without constructing them. 
At runtime, another class is responsible for providing these dependencies
* For DI we are using Hilt 


### Presentation layer (app module)
The layer that interacts with the UI. for this layer we are applying MVVM architecture pattern

### Domain Layer
Contains business logic (usecases/repository interfaces) and entities. 

### Data Layer
Abstract definition of all the data sources. (Network / Local )

### Network layer
Contain abstract and concrete implementation for any logic that is related to network calls. 

### Local Layer
Implementation for local data persistence. App's source of truth for data

