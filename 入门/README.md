#基础
    1，安装sdk
    2，配置路径JAVA_HOME, CLASSPATH, PATH
    set JAVA_HOME=C:\Program Files\Java\j2sdk1.4.2_19
    echo %JAVA_HOME%
    set CLASSPATH=.;%JAVA_HOME%\lib
    echo %CLASSPATH%
    set PATH=%JAVA_HOME%\bin;%PATH%
    PATH

javac java 命令使用参数

C:\workspace\source\tianpusen\java> javac Hello.java
C:\workspace\source\tianpusen\java> java Hello
hello world

# 使用包的情况
C:\workspace\source\tianpusen\java> javac .\tian\pusen\Hello.java
C:\workspace\source\tianpusen\java> java tian/pusen/Hello
hello world

# 没有main包也可以执行
C:\workspace\source\tianpusen\java> javac .\OddHello.java
C:\workspace\source\tianpusen\java> java OddHello
static block hello world
Exception in thread "main" java.lang.NoSuchMethodError: main
