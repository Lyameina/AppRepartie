#!/bin/bash

###
#
# compile.sh - apprep :: twitter
#
# Author Maylanie Mesnier [mesnier@polytech.unice.fr]
# Author Camille Boinaud [boinaud@polytech.unice.fr]
# Version 22/05/15
#
###

# Launch ActiveMQ
./lib/apache-activemq-5.9.0/bin/activemq start

# Create directory which receive .class files
[ -d bin ] || mkdir bin

# Compile .java files, including .jar's librairies path
javac -cp "lib/apache-activemq-5.9.0/activemq-all-5.9.0.jar:." src/*/*.java -d bin/
