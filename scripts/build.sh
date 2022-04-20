#!/bin/bash
# Builds and assembles the distribution of the CLI

# Build (and run tests)
./gradlew build

# Create the install distributions
./gradlew --quiet :photo-album:installDist
