
# makesomenoise
## My first Android application.

### First attempt to create a Android application, using Android Studio 3.
___
Push a button, or move your phone, to make some noise.
___
Simple fun app to play a sound when you touch a button, or when you
whip your phone.
This app is 1st attempt to create an Android app, but it is quite fun
to use. You can virtually whip your find, or make fun of them. You can
play the Dun Dun Duuun or Suspens sound when someone is down during a
murder party :-) . Did you see some The Big Bang Theory episode when
they whip each other or play a murder party ? The sounds are almose the
same, and you can use the app like they do it on the show.

The sounds can't be changed (it will come in a future version).
___

Sounds played :
- Whip : to make you work harder (like in The Big Bang Theory S5E19)
- Dun dun duuuun : the drama sound (also like in The Big Bang Theory S7E18) to increase dramatic suspense of the situation
- Suspens : a famous sunspens sound 
- Laugh : a crowd laugh when someone did a good joke
- Bad Joke : the drums sounds for a not-so-good joke


The last played sound can be replayed by pushing the icon at the bottom of the screen, or by whipping the phone.

The wav sound files are stored in [app/src/main/res/raw](./app/src/main/res/raw) .

APK is available in [app/release/](app/release/)

TODO
- Allow sound configuration depending on the phone move

![Make some noise screenshot](/fastlane/metadata/android/en-US/images/phoneScreenshots/1.png?raw=true)

[Speaker icons created by Freepik - Flaticon](https://www.flaticon.com/free-icons/speaker)

Sensor code inspired by https://f-droid.org/fr/packages/at.h4x.awhip/

Publishing on F-droid : see https://gitlab.com/fdroid/fdroiddata/blob/master/CONTRIBUTING.md
- My F-Droid fork : https://gitlab.com/psa-jforestier/fdroiddata/
- Metadata : https://gitlab.com/psa-jforestier/fdroiddata/-/blob/org.jfo.app.makesomenoise/metadata/org.jfo.makesomenoise.yml
- change build version in "Open Module Setting (F4) > Modules > Version code and Version name" or open app/build.gradle
  Version code : an integer, should represent the same as Version name (MajorMinor)
  Version name : the number as "Major.Minor"
- In fastlane/metadata/android/en-US/changelogs/ create a file "MajorMinor.txt" (save as version code) and indicate change
- Commit repo change with a commit named "vMajor.Minor" and push
- Do a Git Tag named "MajorMinor" (as version code)
