# Reung Box - Movie App

The goal of Reung Box is create a sample movie app with Jetpack Compose.

## How to Use 

**Step 1:**

Download or clone this repo by using the link below:

```
git@github.com:David-GIC/reung-box.git
```

**Step 2:**

Go to project root and sync gradle, after that run it with Debug mode or build release apk

```
./gradlew assembleRelease
```

### Clean Architecture Structure + MVVM pattern
Here is the core folder structure which flutter provides.

```
com.daviddev.reungbox/
|- data
|- domain
|- ui
|- MainActivity
```

```
assets – This directory contains all the resources such as fonts, images, lotties and locale translations.
```

Here is the folder structure we have been using in this project

```
data/
|- client/
|   - HttpClient.kt
|- local/
|   - DataStore.kt
|   - SharePreference.kt
|- remote/
|   - MovieAPI.kt
domain/
|- models/
|   - MovieModel.kt
|- repository/
ui/
|- components/
|- screens/
|- theme/
utils/
|- CommonUtils
|- Constant
|- Routes
|- ImageAsset
|- ErrorStatus
```

Now, lets dive into the com.daviddev.reungbox folder which has the main code for the application.

```
1. data - This directory contains client for Api Instance, local for local data storage and remote for api call.
2. domain - This directory contain model and use case.
3. ui - This directory contain screen UI, glocal components and theme of the app. 
4. utils - This directory contain common utils of the app, constant value, route object, image asset utils and error status.
```

## Demo Screen

![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image.jpeg)
![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image1.jpeg)
![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image2.jpeg)
![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image3.jpeg)
![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image4.jpeg)
![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image5.jpeg)
![Alt text](https://raw.githubusercontent.com/David-GIC/video_demo/master/Image6.jpeg)
