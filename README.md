# photo-album

## Dependencies
* Kotlin - `1.6.20`
* JDK - `17` (Recommend using [Amazon Corretto](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html))
* Kotest test framework
* Clikt command line application framework for Kotlin

## Architecture
This project uses a Gradle multi-module architecture to better separate concerns.

* `photo-album`: the main entrypoint for the application. This contains integrations with third-party libraries.
* `photo-album-core`: services and business logic for the application.
* `photo-album-models`: data models for the application.

## Building
In terminal, run `./gradlew build`. This command will compile the code and run tests.

## Distributing
In order to run the `photo-album` console app, the proper installation distribution must be built. 
Running `./gradlew :photo-album:installDist` will build `.sh` and `.bat` distributions for the application. 


> Included in this project is a bash script which runs `./gradlew build` and `installDist` at once: `./scripts/build.sh`

## Running
The easiest way to test the application is by permanently adding the installation directory to your `PATH`.

This is done by opening your shell of choice's configuration file (`open .zshrc` for example) and appending the installation path of `photo-album`:
```
export PATH=$PATH:/PATH_TO_PHOTO_ALBUM_REPO/photo-album/photo-album/build/install/photo-album/bin
```

After opening a fresh terminal window, you can call the `photo-album` command globally:
```
photo-album 3
```

If you do not want to add it to `PATH` then you can just run with the full path in your terminal:

(within the root `photo-album` directory)
```
./photo-album/build/install/photo-album/bin/photo-album YOUR_ARGS_HERE
```

As the `installDist` command builds a `.bat` equivalent, these steps are similar for Windows users.