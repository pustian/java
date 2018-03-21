http://logging.apache.org/log4j/1.2/faq.html#noconfig

其实您也可以完全不使用配置文件，而是在代码中配置Log4j环境。但是，使用配置文件将使您的应用程序更加灵活。
Log4j支持两种配置文件格式，一种是XML格式的文件，一种是Java特性文件
1.配置根Logger，其语法为：
    log4j.rootLogger = [ level ] , appenderName, appenderName, …
    其中，level 是日志记录的优先级，分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL或者您定义的级别。
    Log4j建议只使用四个级别，优 先级从高到低分别是ERROR、WARN、INFO、DEBUG。
    通过在这里定义的级别，您可以控制到应用程序中相应级别的日志信息的开关。
    比如在这里定 义了INFO级别，则应用程序中所有DEBUG级别的日志信息将不被打印出来。
    appenderName就是指B日志信息输出到哪个地方。您可以同时指定多个输出目的地。
2.配置日志信息输出目的地Appender，其语法为：
    log4j.appender.appenderName = fully.qualified.name.of.appender.class
    log4j.appender.appenderName.option1 = value1
    …
    log4j.appender.appenderName.option = valueN

    Log4j提供的appender有以下几种：fully.qualified.name.of.appender.class
    org.apache.log4j.ConsoleAppender（控制台），
    org.apache.log4j.FileAppender（文件），
    org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），
    org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件），
    org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）

3.配置日志信息的格式（布局），其语法为：
    log4j.appender.appenderName.layout = fully.qualified.name.of.layout.class
    log4j.appender.appenderName.layout.option1 = value1
    …
    log4j.appender.appenderName.layout.option = valueN

    Log4j提供的layout有以下几种：fully.qualified.name.of.layout.class
    org.apache.log4j.HTMLLayout（以HTML表格形式布局），
    org.apache.log4j.PatternLayout（可以灵活地指定布局模式），
    org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串），
    org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等等信息）

4.Log4J采用类似C语言中的printf函数的打印格式格式化日志信息，打印参数如下： %m 输出代码中指定的消息
    %p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
    %r 输出自应用启动到输出该log信息耗费的毫秒数
    %c 输出所属的类目，通常就是所在类的全名
    %t 输出产生该日志事件的线程名
    %n 输出一个回车换行符，Windows平台为“rn”，Unix平台为“n”
    %d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，
        比如：%d{yyy MMM dd HH:mm:ss,SSS}，输出类似：2002年10月18日 22：10：28，921
    %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。
        举例：Testlog4.main(TestLog4.java:10)


三、Web项目中使用Log4j实例

四、Spring中使用Log4j