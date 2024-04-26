#!/bin/bash

rm -fr bin bin-instr
mkdir bin
rm *-instr*

cp ../../target/ex-0.0.1.jar.original bin/
unzip -j ../../target/ex-0.0.1.jar -i *.jar -d lib

# -------------------------------------------------------------------------------------------------------------------------------------------
# list dependencies
# jar files located in `pwd` and subdirectories`
#
FILES=`find . -iname "*.jar*"`
CP_LIST=""
for FILE in $FILES
do
CP_LIST=$CP_LIST:${FILE:2}
done
CP_LIST=$CP_LIST:/usr/local/lib/jazzer_standalone_deploy.jar

echo $CP_LIST

# -------------------------------------------------------------------------------------------------------------------------------------------
# make compilation cmd
#
JAVAC="javac -cp ./$CP_LIST -encoding ISO-8859-5 java/my/sample/sample/harness.java -d bin"

# -------------------------------------------------------------------------------------------------------------------------------------------
# make running script
#
JAVA="java -cp ./bin$CP_LIST harness"
echo $JAVA > run_jazz.sh
chmod +x run_jazz.sh

# -------------------------------------------------------------------------------------------------------------------------------------------
# make fuzzing cmd
# 
FUZZ="/jazzer/jazzer \
 --cp=./bin/harness.jar$CP_LIST \
 --target_class=HarnessFuzzing \
 --reproducer_path=repro \
 --trace=all:gep \
 -use_value_profile=1 \
 -print_final_stats=1 \
 -- in_out"

echo $FUZZ > fuzz_jazz.sh
chmod +x fuzz_jazz.sh

# -------------------------------------------------------------------------------------------------------------------------------------------
# run compilation cmd
#
$JAVAC -Xlint:deprecation
cd bin
jar cvf harness.jar *
