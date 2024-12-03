#!/bin/bash
this_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$this_path"
# The code above makes all the locations in the script be relative to the script location (independently of where it's invoked from)
# SRC: https://stackoverflow.com/questions/24112727/relative-paths-based-on-file-location-instead-of-current-working-directory

# Clean / Compile / Create FAT-Jar file
./mvnw clean package

# Start docker compose
docker compose up --build