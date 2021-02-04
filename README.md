# Sights Locator

Sights Locator is an Android application for searching and viewing information about famous landscapes near you (currently only island Krk in Croatia is available as a destination). Sights Locator is written in Java using Model-View-ViewModel pattern.

## Running

Download project and open it with Android Studio. Open `local.properties` and **add Google Map API key** which you can obtain here: [https://developers.google.com/maps/documentation/android-sdk/get-api-key](https://developers.google.com/maps/documentation/android-sdk/get-api-key). Add generated key in the file as follows:

```
sdk.dir=....
MAPS_API_KEY=1234...
```

Start the application on Android with **SDK version 28** or higher.

## GUI

### Splash screen

Splash screen uses RxJava (ReactiveX) observable to download data from the REST API (using Retrofit) and storing it in a local database (using RoomDAO). Splash screen also show few animations while the user waits for the data.

<p align="center"><img src="https://github.com/SanjinKurelic/SightsLocator/blob/master/media/splashScreen.png" width="320"/></p>

### List of sights

After all data is downloaded/stored user is presented with a list of sights. The list is presented in RecyclerView that uses CardView layout (ViewHolder pattern). 

<p align="center"><img src="https://github.com/SanjinKurelic/SightsLocator/blob/master/media/listView.png" width="320"/></p>

It's possible to delete items from the list by swiping to the right.

<p align="center"><img src="https://github.com/SanjinKurelic/SightsLocator/blob/master/media/listSwipe.png" width="320"/></p>

### Item details

By clicking the item on the list or click the button in navigation, users can see more info about sight (using ViewPager).

<p align="center"><img src="https://github.com/SanjinKurelic/SightsLocator/blob/master/media/itemDetails.png" width="320"/></p>

### Google Map

From the bottom menu user can view all the nearby sights interactively on the map. By clicking on the marker user gets more info about sight.

<p align="center"><img src="https://github.com/SanjinKurelic/SightsLocator/blob/master/media/mapScreen.png" width="320"/></p>

### Exit

User can also exit the application by pressing the most left button. Before exiting confirmation prompt will be raised.

<p align="center"><img src="https://github.com/SanjinKurelic/SightsLocator/blob/master/media/exitPrompt.png" width="320"/></p>

## Technologies

Technologies and patterns:

- Java
- MVVM
- SOC, SRP, Contractors
- ViewBinding & DataBinding
- Fragments
- Bottom navigation
- RecyclerView
- Adapters
- ViewHolder
- ViewPager
- NavigationDrawer
- Google maps
- Animations

Dependencies:

- AndroidX AppCompat
- AndroidX ConstraintLayout
- AndroidX NavigationUI
- AndroidX Preference
- AndroidX RoomDAO
- Lombok
- Picasso & Picasso transformations
- ReactiveX (RxAndroid, RxJava)
- Retrofit
- RippleBackground
