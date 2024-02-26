#!/bin/bash

files=()
while read -r filename; do
  baseFileName="$(basename "$filename")"
  files+=(-F "$baseFileName=@$filename")
done < <(find ./buildAllJars/ -name '*.jar' -type f)

curl --fail -X POST -H "Authorization: Bearer ${{ secrets.DEPLOY }}" "${files[@]}" https://api.mrmelon54.com/v1/mc-upload/upload/clock-hud
