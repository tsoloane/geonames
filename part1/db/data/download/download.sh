#!/bin/bash

#remove all text and zip files
rm -f *.zip *.txt

GEONAME_PREFIX="https://download.geonames.org/export/dump"
POSTCODE_PREFIX="https://download.geonames.org/export/zip"
ALTERNAME_PREFIX="https://download.geonames.org/export/dump/alternatenames"

processCountry() {
  # Download the main geonames file.
  if (curl -o/dev/null -sfI "$GEONAME_PREFIX/$1.zip"); then
    curl "$GEONAME_PREFIX/$1.zip" -s -o "geonames-$1.zip"
    unzip "geonames-$1.zip" -x readme.txt
    mv "$1.txt" "geonames-$1.txt"
    sed -i 's/\"/""/g' "geonames-$1.txt" #Escape quote character to avoid issues importing.
    docker exec -it geonames-db psql -U geonames_admin geonames_db -c "\copy geo_name from '/db/download/geonames-$1.txt' CSV DELIMITER E'\t'"
  else
    echo "No Geonames file for $1"
  fi
  echo "- Done with geonames"

  #Download the alternate names file
  if (curl "$ALTERNAME_PREFIX/$1.zip" -s -o "alternames-$1.zip"); then
    curl "$ALTERNAME_PREFIX/$1.zip" -s -o "alternames-$1.zip"
    unzip "alternames-$1.zip" -x readme.txt
    mv "$1.txt" "alternames-$1.txt"
    sed -i 's/\"/""/g' "alternames-$1.txt"
    docker exec -it geonames-db psql -U geonames_admin geonames_db -c "\copy alternate_name from '/db/download/alternames-$1.txt' CSV DELIMITER E'\t'"
  else
    echo "No alternate names file found for $1"
  fi
  echo "-- Done with alternate names"

  # Now manage the postal codes.
  #Check if the file exists then download else report absence
  if (curl -o/dev/null -sfI "$POSTCODE_PREFIX/$1.zip"); then
    curl "$POSTCODE_PREFIX/$1.zip" -s -o "postal_codes-$1.zip"
    unzip "postal_codes-$1.zip" -x readme.txt
    mv "$1.txt" "postal_codes-$1.txt"
    sed -i 's/\"/""/g' "postal_codes-$1.txt"
    docker exec -it geonames-db psql -U geonames_admin geonames_db -c "\copy postal_code from '/db/download/postal_codes-$1.txt' CSV DELIMITER E'\t'"
  else
    echo "No postal codes available for $1"
  fi
  echo "-- Done with postal codes"
}

if [[ $# -eq 0 ]]; then
  echo "No country specified, defaulting to ZA"
  processCountry "ZA"
else
  for code in "$@"; do
    echo "Processing country $code"
    processCountry "$code"
  done
fi
echo "Done"
