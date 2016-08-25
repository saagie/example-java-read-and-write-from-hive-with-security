Example for reading & writing into Hive with security
==================================

Package for saagie : mvn package and get the package in target.

Usage in local :

 - mvn package
 - java -jar target/example-java-read-and-write-from-hive-with-security-1.0-SNAPSHOT-jar-with-dependencies.jar jdbc:hive2://hiveserver:10000/;ssl=false

Usage in Saagie :

 - mvn package (in local, to generate jar file)
 - create new Java Job
 - upload the jar (target/example-java-read-and-write-from-with-security-1.0-SNAPSHOT-jar-with-dependencies.jar)
 - copy URL from Hive connection details panel and add it in first argument in the command line
 - copy user environment variable and add it in second argument in the command line
 - copy password environment variable and add it in third argument in the command line
 - choose java 8
 - create and launch.
