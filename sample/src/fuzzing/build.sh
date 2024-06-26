#!/bin/bash

rm -fr bin bin-instr lib
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
CP_LIST=$CP_LIST:`pwd`/${FILE:2}
done
CP_LIST=$CP_LIST:/usr/local/lib/jazzer_standalone_deploy.jar

echo $CP_LIST

# -------------------------------------------------------------------------------------------------------------------------------------------
# make compilation cmd
#
JAVAC="javac -cp ./$CP_LIST -encoding ISO-8859-5 *.java -d bin"

# -------------------------------------------------------------------------------------------------------------------------------------------
# make running script
#
# JAVA="java -cp ./bin$CP_LIST harness"
# echo $JAVA > run_jazz.sh
# chmod +x run_jazz.sh

# -------------------------------------------------------------------------------------------------------------------------------------------
# make sydr-fuzz.toml
# 
TOML="
exit-on-time = 300\n
\n
[jazzer]\n
target_class = \"HarnessFuzzer\"\n
args = \"-jobs=2 --cp=`pwd`/bin$CP_LIST in\"
"
echo -e $TOML > sydr-fuzz.toml

# -------------------------------------------------------------------------------------------------------------------------------------------
# make jazzer fuzzing cmd
# 
# FUZZ="jazzer \
# --agent_path=/usr/local/lib/jazzer_standalone_deploy.jar \
# --cp=`pwd`/bin$CP_LIST \
# --target_class=HarnessFuzzer \
# --reproducer_path=repro \
# --trace=all:gep \
# -use_value_profile=1 \
# -print_final_stats=1 \
# -- in"

echo $FUZZ > fuzz_jazz.sh
chmod +x fuzz_jazz.sh

# -------------------------------------------------------------------------------------------------------------------------------------------
# run compilation cmd
#
$JAVAC -Xlint:deprecation
cd bin
jar cvf harness.jar *
