#!/bin/sh

echo "==================== Note: All build jars will be in the folder called 'buildAllJars' ===================="

# Loop trough everything in the version properties folder
for d in versionProperties/*; do
  # Get the name of the version that is going to be compiled
  version=$(echo "$d" | sed "s/versionProperties\///" | sed "s/.properties//")
  mcmodupdater -mc "$version" -f "$d"
done
