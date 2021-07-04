# MERCADO-LIBRE-GHALLENGE-BE-API

# Introduction
This project contains the implementation of the purchase coupon API, challenge for Mercado Libre.

# What you need
+ A favorite text editor or IDE
+ JDK 11 or later
+ Install Gradle 6.8.3


### Build your project with Gradle 

```
------------------------------------------------------------
compile artifact command
------------------------------------------------------------

./gradle clean build
./gredle install

------------------------------------------------------------
generate jacoco report
------------------------------------------------------------
./gradle jacocoTestReport

```


### Build your project with Gradle Wrapper

```
------------------------------------------------------------
compile artifact command
------------------------------------------------------------

./gradlew clean build
./gredlew install

------------------------------------------------------------
generate jacoco report
------------------------------------------------------------
./gradle jacocoTestReport


```


### Dependency Management

the project dependency management are in the file app-dependencies.gradle

```
Example:

  SPRING_BOOT_RUNTIME = [
            [group: 'org.springframework.boot', name: 'spring-boot-starter-actuator'],
            [group: 'org.springframework.boot', name: 'spring-boot-starter-web'],
            [group: 'org.springframework', name: 'spring-context-support'],
    ]

```


###  Files Clean Code

Task files for clean code good practice validation

+ code-quality-config.gradle
+ cv-framework-config.gradle

The configuration files of the rules for checkstyle, pmd and spotbugs.


```

    └── config
        └── checkstyle
            └── checkstyle.xml
        └── pmd
            └── pmd-ruleset.xml
        └── spotbugs
            └── spotbogs-exclude.xml        
```







