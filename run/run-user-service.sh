#!/bin/bash

# do maven package
mvn clean package;

# Run the process
java -jar user-provision/target/*.jar &
