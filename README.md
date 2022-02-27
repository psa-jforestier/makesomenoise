
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
play the Dun Dun Duuun or Suspense sound when someone is down during a
murder party :-) . Did you see some The Big Bang Theory episode when
they whip each other or play a murder party ? The sounds are almose the
same, and you can use the app like they do it on the show.

The sounds can't be changed (it will come in a future version).
___

Sounds played :
- Whip : to make you work harder (like in The Big Bang Theory S5E19)
- Dun dun duuuun : the drama sound (also like in The Big Bang Theory S7E18) to increase dramatic suspense of the situation
- Suspense : a famous sunspens sound 
- Laugh : a crowd laugh when someone did a good joke
- Bad Joke : the drums sounds for a not-so-good joke


The last played sound can be replayed by pushing the icon at the bottom of the screen, or by whipping the phone.
You can also use the keyboard (Key 1 to 5, and R for repeating the last sound).

The wav sound files are stored in [app/src/main/res/raw](./app/src/main/res/raw) .

APK is available in [app/release/](app/release/)

TODO
- Allow sound configuration depending on the phone move

![Make some noise screenshot](/fastlane/metadata/android/en-US/images/phoneScreenshots/1.png?raw=true)

[Speaker icons created by Freepik - Flaticon](https://www.flaticon.com/free-icons/speaker)

Sensor code inspired by https://f-droid.org/fr/packages/at.h4x.awhip/
Sounds are made by [myself](https://freesound.org/people/JayRom01/), or came from [Simon Lacelle](https://freesound.org/people/Simon_Lacelle/) or from Dick De Benedictis. All of them are open and free to use.


[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
alt="Get it on F-Droid"
height="80">](https://f-droid.org/packages/org.jfo.app.makesomenoise)

<a href='https://play.google.com/store/apps/details?id=org.jfo.app.makesomenoise&pcampaignid=fromGitHub'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' height=80/></a>

Publishing on F-droid : see https://gitlab.com/fdroid/fdroiddata/blob/master/CONTRIBUTING.md
- My F-Droid fork : https://gitlab.com/psa-jforestier/fdroiddata/
- Metadata : https://gitlab.com/psa-jforestier/fdroiddata/-/blob/org.jfo.app.makesomenoise/metadata/org.jfo.app.makesomenoise.yml
- change build version in "Open Module Setting (F4) > Modules > Version code and Version name" or open app/build.gradle
  Version code : an integer, should represent the same as Version name (MajorMinor)
  Version name : the number as "Major.Minor"
- In fastlane/metadata/android/en-US/changelogs/ create a file "MajorMinor.txt" (save as version code) and indicate change
- Commit and push all the changes
- Do a Git Tag named "MajorMinor" (as version code) : Github > Tags > Releases : Draft a new release, create a tag named "Major.Minor" in "choose tag", name the release as "Major.Minor"


Publishing on Google Play :
- https://support.google.com/googleplay/android-developer/answer/9859751?hl=fr
- Have a developer account : https://play.google.com/apps/publish/signup/
