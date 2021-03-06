# PGMacUtilities
This will be a collection of utility methods that I use in nearly all of my projects

## Installation

To install, insert this into your build.gradle file 

```java

allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}

```

And include this in your dependencies section:

```java

compile ('com.github.PGMacDesign:PGMacUtilities:0.0.36')

```

Having trouble with Jitpack? [This link](https://jitpack.io/#pgmacdesign/PGMacUtilities) here will show what is going on with the current build as well as give you instructions on integrating Jitpack into your project. 

## Nested Libraries

This library utilizes many others within it. Below is a listing of all of the nested libraries this project utilizes under the hood. If you wish to use a class, method, or function that uses one of these other libraries, make sure to include it in your project.

```java

        //Secure Shared Prefs. Link: https://github.com/scottyab/secure-preferences
        compile 'com.scottyab:secure-preferences-lib:0.1.4'

        //Progress bar animator. This one is better for longer processes, IE uploading photos.
        //Link: https://github.com/Tibolte/ElasticDownload
        compile 'com.github.tibolte:elasticdownload:1.0.4'

        //Image Cropper. https://github.com/Yalantis/uCrop
        compile 'com.yalantis:ucrop:2.2.0'

        //Animated SVG View (Custom, takes an SVG To work).
        //Link: https://github.com/jaredrummler/AnimatedSvgView
        compile 'com.jaredrummler:animated-svg-view:1.0.5'

        //Volley. Link: https://github.com/google/volley
        compile 'com.android.volley:volley:1.0.0'

        //GSON. Link https://github.com/google/gson
        compile 'com.google.code.gson:gson:2.8.1'

        //Picasso. Link: https://github.com/square/picasso
        compile 'com.squareup.picasso:picasso:2.5.2'

        //Animations Base. Link: https://github.com/JakeWharton/NineOldAndroids
        compile 'com.nineoldandroids:library:2.4.0'

        //Retrofit, Retrofit Converters, and OKHTTP.
        //Retrofit - https://github.com/square/retrofit
        //OKHttp - https://github.com/square/okhttp
        compile 'com.squareup.retrofit2:retrofit:2.3.0'
        compile 'com.squareup.okhttp3:okhttp:3.9.0'
        compile 'com.squareup.okhttp3:logging-interceptor:3.9.0'
        //Type-safe HTTP client for Android and Java: https://github.com/square/retrofit
        compile 'com.squareup.retrofit2:converter-gson:2.3.0'
        //OKIO. Link: https://github.com/square/okio
        compile 'com.squareup.okio:okio:1.13.0'

        //Part of The Android Animations collection below
        compile 'com.daimajia.easing:library:2.0@aar'
        //Android Animations. Link: https://github.com/daimajia/AndroidViewAnimations
        compile 'com.daimajia.androidanimations:library:2.2@aar'

        //Text View + Animations. Link: https://github.com/hanks-zyh/HTextView
        compile 'hanks.xyz:htextview-library:0.1.5'


        //////////////////////////////////////////////////////
        //Recommended to use if utilizing multiple libraries//
        //////////////////////////////////////////////////////

        //Multi-dex. For more info: https://developer.android.com/studio/build/multidex.html
        compile 'com.android.support:multidex:1.0.1'

```

## Known Issues

Depending on your version of Google's Libraries, you may run into this error:

```java
Error:Execution failed for task ':app:processDebugManifest'.
> Manifest merger failed : Attribute meta-data#android.support.VERSION@value value=(26.0.1) from [com.android.support:design:26.0.1] AndroidManifest.xml:28:13-35
	is also present at [com.android.support:appcompat-v7:26.1.0] AndroidManifest.xml:28:13-35 value=(26.1.0).
	Suggestion: add 'tools:replace="android:value"' to <meta-data> element at AndroidManifest.xml:26:9-28:38 to override.
```

Or something along those lines. If you do, simply add this line of code to your build.gradle file underneath the Android Tag

```java
    configurations.all {
        resolutionStrategy.eachDependency { DependencyResolveDetails details ->
            def requested = details.requested
            if (requested.group == 'com.android.support') { //Replace String here with whichever error is thrown
                if (!requested.name.startsWith("multidex")) {
                    details.useVersion '26.0.2' //Replace version here with whatever you are using
                }
            }
        }
    }
```	

## New Issues

If you run into any compatability issues or bugs, please open a ticket ASAP so I can take a look at it. 

## Important Notes

Please keep in mind that as this is still in the beta phase, it will change dramatically before launch. 