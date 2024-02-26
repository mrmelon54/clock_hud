#!/bin/sh

echo "==================== Note: All build jars will be in the folder called 'buildAllJars' ===================="
mkdir -p buildAllJars | true

version="$1"

# Clean out the folders, build it, and merge it
# (We could use "./" to run gradlew, but as it is a shell script im going to be running it with the "sh" command)
echo "==================== Cleaning workspace to build $version ===================="
sh gradlew clean -PmcVer="$version" --no-daemon || true
echo "====================Building $version ===================="
sh gradlew build -PmcVer="$version" --no-daemon || true
echo "==================== Merging $version ===================="
sh gradlew mergeJars -PmcVer="$version" --no-daemon || true
echo "==================== Moving jar ===================="
mv Merged/*.jar buildAllJars/ || true
# The "| true" at the end of those are just to make sure the script continues even if a build fails
