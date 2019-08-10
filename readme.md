# License

This project is in pre-release; it is intended to have the Apache license but is not ready for consumption by the general public.  If you would like to try this application and/or contribute with code and feedback, feel free.

# Summary

A collection of Java Swing demos including layout demos.

# Install a local JAR to local repo
```
mvn install:install-file 
-Dfile=./nimrodlf-1.2d.jar 
-DgroupId=com.nilo.plaf 
-DartifactId=nimrod 
-Dversion=1.2d 
-Dpackaging=jar
```

# Misc/Notes
Eclipse Run Configuration > Arguments > VM Arguments:

```
-Dswing.plaf.metal.userFont=Consolas-20
-Dswing.plaf.metal.controlFont="Calibri-20"
```
