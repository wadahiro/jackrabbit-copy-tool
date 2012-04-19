JackRabbit Copy Tool
========================
https://github.com/wadahiro/jackrabbit-copy-tool

# How to use
1. Build by `gradlew distZip`.
2. Copy $projectDir/build/distributions/jackrabbit-copy-tool-{version}.zip to your JackRabbit server, and unzip this zip file.
```
unzip jackrabbit-copy-tool-{version}.zip
```
3. `cd jackrabbit-copy-tool-{version}/bin` and execute `jackrabbit-copy-tool` command.

Usage:

```
jackrabbit-copy-tool -sc <path to source repository.xml> \
                -sd <path to source repository home directory> -tc <path to target repository.xml> \
                -td <path to target repository home directory>
```

Params:

*  **-sc**
        Path to source repository.xml.
*  **-sd**
        Path to source home directory.
*  **-tc**
        Path to target repository.xml.
*  **-td**
        Path to target home directory.

Example:

```
jackrabbit-copy-tool -sc /jackRabbitDir/repository.xml -sd /jackRabbitDir/home -tc /anotherJackRabbitDir/repository.xml -td /anotherJackRabbitDir/home
```

# Notes
This tool support MySQL for source or target repository persistence by default.
If you want to use another DB, you can add dependencies of another DB driver by build.gradle.

# License
* [Apache Licence 2.0]
