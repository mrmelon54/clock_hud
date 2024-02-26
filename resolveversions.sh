#!/bin/bash

versions=()

function prop {
  grep "${2}" "${1}" | cut -d'=' -f2
}

# Loop trough everything in the version properties folder
for d in versionProperties/*; do
  # Get the name of the version that is going to be compiled
  version=$(echo "$d" | sed "s/versionProperties\///" | sed "s/.properties//")
  javaVersion=$(prop "$d" "java_version")
  versions+=("{\"mc\":\"$version\",\"java\":\"$javaVersion\"}")
done

versionsJson=$(jo -a -- "${versions[@]}")

echo "matrix={\"include\":${versionsJson}}"
