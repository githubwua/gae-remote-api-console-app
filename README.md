# Purpose
To illustrate how to use Remote API in a local Java console app to access Google App Engine (GAE) services remotely.

# To run
```
mvn exec:java -Dexec.mainClass=wua.eg.gae.remoteapi.App -Dexec.args="$GAE_HOST"
```

where $GAE_HOST is the remote GAE host.  It can be localhost or xxxx.appspot.com

Alternatively, we could also copy the required dependency libraries with Maven and then run the app with the java command directly without Maven.

For example,

```
# Copy dependency libraries to folder ./target/
mvn -DoutputDirectory=target dependency:copy-dependencies

# Run it (with .class and .jar files in classpath, the main class, and arguments)
java -cp target/classes/:target/* wua.eg.gae.remoteapi.App $GAE_HOST
```

Make sure to replace $GAE_HOST with the actual GAE hostname (e.g. xxxx.appspot.com or localhost for local development server)

# Maven Template used
mvn archetype:generate \
 -DgroupId=wua \
 -DartifactId=gae-remote-api-console-app \
 -Dpackage=wua.eg.gae.remoteapi \
 -DarchetypeArtifactId=maven-archetype-quickstart \
 -DinteractiveMode=false

# Reference
https://cloud.google.com/appengine/docs/java/tools/remoteapi