find . -name "pom.xml" -not -path "./diffutils/*"  -exec mvn versions:set -DnewVersion=$1 -f {} \;


