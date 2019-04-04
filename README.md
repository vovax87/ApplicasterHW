# Applicaster example 

I implementing REST API in the Android app using Retrofit Library by using MVVM ,  Android Architecture Components introduce by Google

There are three parts of the Model-View-ViewModel architecture:

1 Model is used to write Business logic. The model represents the actual data and/or information we are dealing with.

2 View is used to consume data/result received from ViewModel and inform ViewModel of user interaction.In simple way, we can say that it is used to display data to user.

3 ViewModel works as a bridge between Model and View. It is responsible to process data using Model and send back the result to View to consume it. It contains UI Logic that involving both View and Model. A ViewModel is retained as long as the scope of its Activity/Fragment is alive, including when the Activity/Fragmentis destroyed and recreated due to a configuration change; This allows ViewModel to make UI data available to the recreated activity or fragment instance. Wrapping UI data stored within the ViewModel with LiveData provides the data an observable lifecycle-aware home.


![alt text](https://cdn.journaldev.com/wp-content/uploads/2018/04/android-mvvm-pattern.png)



3rd party librareis - glide,exoplayer,retrofit,gson 

The app is competinle for Tablets as well

![alt text](https://i.ibb.co/KDYvVm1/Screen-Shot-2019-03-27-at-12-30-41.png)






License
----

MIT

**Free Software, For all**

