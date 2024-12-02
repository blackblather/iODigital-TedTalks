#!/bin/bash
this_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$this_path"
# The code above makes all the locations in the script be relative to the script location (independently of where it's invoked from)
# SRC: https://stackoverflow.com/questions/24112727/relative-paths-based-on-file-location-instead-of-current-working-directory

# Apply database migrations
echo "* APPLYING DATABASE MIGRATIONS (IF APPLICABLE)"
./evolve migrate postgresql -c "Server=localhost;Database=postgres;User Id=postgres;Password=admin123;" \
                            -l "sql-scripts" \
                            -s public
