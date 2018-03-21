LoggingMainApp 主要演示的是logger的默认级别和console端直接设置level是无用的
PropertiesLoggingMain 主要演示logger加载log.properties 并且setLevel生效
HandlerLoggingMain 演示输出格式化和setLevel转换


日志(Log)的配制:
1.代码设置
    使用setLevel();但这种方式不能改变console的级别，只能改变输出到文件的日志的级别。
2.修改logging.properties
    默认的外部配置文件 是JRE中lib/logging.properties文件。你可以打开这个文件，修改以下两行为：
    .level=ALL
    //...
    java.util.logging.ConsoleHandler.level = ALL
    这种方式会影响jre下所有用户。

    为了不影响到所有的用户，我们还可以通过LogManager的readConfiguration(InputStream ins)读取指定的配制文件。

Handler
Formatter