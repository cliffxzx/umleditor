# UML Editor - MVC, OOP and Design Patterns Practice

## How did I start this project

### Initialize Project

```shell=
mvn archetype:generate -DgroupId=com.cliffxzx -DartifactId=umleditor -Dversion=1.0-SNAPSHOT
```

### Add dependencies

#### Add [reflections](https://github.com/ronmamo/reflections)

- [Reflection in Java](https://www.geeksforgeeks.org/reflection-in-java/)
- [秒懂，Java 註解 （Annotation）你可以這樣學](https://zhuanlan.zhihu.com/p/27643133)

pom.xml

```xml=
...
<dependencies>
  ...
  <dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>0.10.2</version>
  </dependency>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.6.1</version>
  </dependency>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.6.1</version>
  </dependency>
</dependencies>
...
```

### Introduce MVC Architecture

- [Swing-MVC](https://github.com/Allan-Jacobs/Swing-MVC)
- [The MVC pattern and Swing](https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing)

![](https://i.stack.imgur.com/gBfe7.jpg)
