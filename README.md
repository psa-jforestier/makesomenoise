
# makesomenoise
## My first Android application.

### First attempt to create a Android application, using Android Studio 3.

The goal of this app is to play some fun sounds when a button is pressed, or when the phone is moved.

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


Icon from https://www.flaticon.com/premium-icon/megaphone_2275981
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
