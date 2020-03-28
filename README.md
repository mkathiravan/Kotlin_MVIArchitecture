# Kotlin_MVIArchitecture

MVI stands for Model-View-Intent. MVI is one of the newest architecture patterns for Android.

## Model

It is a response to the creation of a new state, our model object, which tells View what to display on the screen.

## View

view() is a function that takes a new state and defines the display logic inside of it.

## Intent

Intent is not the Android intent as we know it. Intent here means intention to change the state of our app by an intent. intent() can be started by the user clicking on a button or as a result of our API call that we want to display on the screen.

The app has following packages:

i) data: It contains all the data accessing and manipulating components.

ii) ui: View classes along with their corresponding ViewModel.

iii) utils: Utility classes.

![image](https://user-images.githubusercontent.com/39657409/77831844-94288b80-7157-11ea-9637-775c51097a5c.png)


