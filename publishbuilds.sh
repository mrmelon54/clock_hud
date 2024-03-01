#!/bin/bash

while read -r filename; do
  curl --fail -X POST -H "Authorization: Bearer ${{ secrets.DEPLOY }}" -F "upload=@$filename" https://api.mrmelon54.com/v1/mc-upload/upload/clock-hud
done < <(find ./buildAllJars/ -name '*.jar' -type f)
