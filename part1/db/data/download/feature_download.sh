#!/bin/bash

#remove all feature files
rm -f featureCodes_*.zip

if [[ $# -lt 1 ]]; then
  printf "Usage: feature_download.sh LANGUAGES
  \t where LANGUAGE is a space separated list of 2 letter ISO language codes\n"
  exit 1
fi

URL_PREFIX="https://download.geonames.org/export/dump/featureCodes"

for code in "$@"; do
  URL="$URL_PREFIX"_$code.txt
  printf "processing language %s\n" "$code"
  if (curl -o/dev/null -sfI "$URL"); then
    curl -O -s "$URL"
    #remove the null record line - it breaks things
    sed -i '/^null/d' "featureCodes_$1.txt"
    #Separate the feature field (1st field into 2 fields)
    sed -i 's/\./\t/' "featureCodes_$1.txt"
    #import to db
    docker exec -it geonames-db psql -U geonames_admin geonames_db -c "\copy feature from '/db/download/featureCodes_$1.txt' "
  else
    printf "unable to find feature code for language %s\n" "$code"
  fi
done

echo "All Done"
