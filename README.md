java-performance-test
=====================

The idea behind this project is to have a solution to implement a small test to prove which implementation of a problem
is the fastest at runtime.

running with maven
------------------
like in the implementation module you can configure the runner to run with maven to create a report

1. add the dependency
```
<dependencies>
    <dependency>
        <groupId>com.perhab.java.performance</groupId>
        <artifactId>napalm-api</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    ...
</dependencies>
```
2. add build plugin definition for the [exec-maven-plugin](http://www.mojohaus.org/exec-maven-plugin/index.html)
```
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>1.6.0</version>
    <executions>
        <execution>
            <id>generate-markdown-report</id>
            <phase>package</phase>
            <goals>
                <goal>exec</goal>
            </goals>
            <configuration>
                <executable>java</executable>
                <arguments>
                    <argument>-classpath</argument>
                    <classpath />
                    <argument>com.perhab.napalm.Runner</argument>
                    <argument>-f</argument>
                    <argument>${project.build.directory}/Results.md</argument>
                    <argument>-s</argument>
                </arguments>
            </configuration>
        </execution>
    </executions>
</plugin>
```
This uses the exec-maven-plugin to call the Runner and scan for classes annotated with Execute. Additionaly it creates
a report output in Results.md and turns the Runner into silent mode.