## Codemotion Workshop project preparation.

### Slides

The slides used to facilitate the workshop can be found [here](https://speakerdeck.com/guardiola31337/architecture-and-testing-android-developers-mantra-codemotion-spain-2015).

### Prerequisites

- Android SDK API 23
- Android SDK Build-tools
- Android Support Library
- Android Studio / IntelliJ + Plugin Scala (SBT)
- Emulator, Genymotion or physical device
- Ruby - Calabash

### Steps:

1. ```cd``` to folder ```codemotion-workshop``` and ```sbt```
2. Open IntelliJ and File>Open>codemotion-workshop and double click in build.sbt
3. SBT tab Click Refresh all SBT projects
4. Project Structure>Project Settings>Modules>app>Dependencies>Module SDK Choose Android API 23 Platform
5. Project Structure>Project Settings>Modules>app>Sources Select java from src/androidTest/ and press Test

### Terminal

```cd``` to folder ```codemotion-workshop``` and ```sbt```

- ```projects``` -> Command to list all modules

```project hexagon```

- ```test``` -> Command to execute tests in hexagon

- ```compile``` -> Command to compile sources in hexagon

- ```clean``` -> Command to clean hexagon module

```project adapters```

- ```test``` -> Command to execute tests in adapters

- ```compile``` -> Command to compile sources in adapters

- ```clean``` -> Command to clean adapters module

```project app```

- ```devices``` -> Command to list connected devices

- ```android:run``` -> Command to install the app

- ```android:compile``` -> Command to compile sources in app

- ```android:test``` -> Command to execute androidTests in app

- ```android:clean``` -> Command to clean app module

### Calabash - Ruby

1. Install ```rbenv``` and ```ruby-build```
  *  You can install ```rbenv``` and ```ruby-build``` using the [Homebrew](http://brew.sh) package
manager on Mac OS X:

  ```sh
  $ brew update
  $ brew install rbenv ruby-build
  ```

2. Add `rbenv init` to your shell to enable shims and autocompletion.

  ```sh
  $ echo 'eval "$(rbenv init -)"' >> ~/.bash_profile
  ```

  _Use `~/.bashrc` on Ubuntu, or `~/.zshrc` for Zsh._

3. Restart your shell so that PATH changes take effect. (Opening a new
   terminal tab will usually do it.) Now check if rbenv was set up:

  ```sh
  $ type rbenv
  #=> "rbenv is a function"
  ```

4. Install Ruby

  ```sh
  # list all available versions:
  $ rbenv install -l

  # install a Ruby version:
  $ rbenv install 2.2.3
  ```

5. rbenv local
 * ```cd``` to folder ```codemotion-workshop``` and
 
 ```sh
 $ rbenv local 2.2.3
 ```

6. Install Calabash
 * ```cd``` to folder ```codemotion-workshop``` and
 
 ```sh
 $ gem install calabash-android
 ```
 
7. Run Calabash tests
 * ```cd``` to folder ```codemotion-workshop``` and
 
 ```sh
 $ calabash-android run ./modules/app/target/android/output/app-debug.apk
 ```

### Following each step

We have included [sbt-groll plugin](https://github.com/sbt/sbt-groll) so you can move around the Git commit history and see the progress.

```cd``` to folder ```codemotion-workshop```, ```sbt``` and ```groll initial```

```
> groll next
```

That will take you to next commit and step. Use ```groll prev``` if you want to go back.