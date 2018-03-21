1, 依赖包
　　log4j-core-xx.jar
    log4j-api-xx.jar
2. 开始使用：
    我们知道，要在某个类中使用log4j记录日志，只需要申明下面的成员变量（其实不一定要是成员变量，只是为了方便调用而已）.
    log4j 2.0的使用非常简单，只要用LogManager的getLogger函数获取一个logger，就可以使用logger记录日志。

    private static Logger logger = LogManager.getLogger(MyApp.class.getName());

　　这里getLogger有一个参数指定的是这个logger的名称，这个名称在配置文件里面可是有需要的，这个待会儿再说。
    声明了Logger对象，我们就可以在代码中使用他了。

3. 日志的级别：
    我们现在要调用logger的方法，不过在这个Logger对象中，有很多方法，所以要先了解log4j的日志级别，
    log4j规定了默认的几个级别：trace<debug<info<warn<error<fatal等。这里要说明一下：
    1）级别之间是包含的关系，意思是如果你设置日志级别是trace，则大于等于这个级别的日志都会输出。
    2）基本上默认的级别没多大区别，就是一个默认的设定。你可以通过它的API自己定义级别。
        你也可以随意调用这些方法，不过你要在配置文件里面好好处理了，否则就起不到日志的作用了，
        而且也不易读，相当于一个规范，你要完全定义一套也可以，不用没多大必要。
        从我们实验的结果可以看出，log4j默认的优先级为ERROR
　　3）这不同的级别的含义大家都很容易理解，这里就简单介绍一下：
　　　　trace： 是追踪，就是程序推进以下，你就可以写个trace输出，所以trace应该会特别多，不过没关系，我们可以设置最低日志级别不让他输出。
　　　　debug： 调试么，我一般就只用这个作为最低级别，trace压根不用。是在没办法就用eclipse或者idea的debug功能就好了么。
　　　　info： 输出一下你感兴趣的或者重要的信息，这个用的最多了。
　　　　warn： 有些信息不是错误信息，但是也要给程序员的一些提示，类似于eclipse中代码的验证不是有error和warn（不算错误但是也请注意，比如以下depressed的方法）。
　　　　error： 错误信息。用的也比较多。
　　　　fatal： 级别比较高了。重大错误，这种级别你可以直接停止程序了，是不应该出现的错误么！不用那么紧张，其实就是一个程度的问题。

4. 日志调用：详情见代码
    在默认情况下，系统选择configuration文件的优先级如下：（classpath为scr文件夹）
    classpath下名为 log4j-test.json 或者log4j-test.jsn文件
    classpath下名为 log4j2-test.xml
    classpath下名为 log4j.json 或者log4j.jsn文件
    classpath下名为 log4j2.xml

5,  简单介绍一下下面这个配置文件。
    1）根节点configuration，然后有两个子节点：appenders和loggers（都是复数，意思就是可以定义很多个appender和logger了）（如果想详细的看一下这个xml的结构，可以去jar包下面去找xsd文件和dtd文件）
        1）appenders：这个下面定义的是各个appender，就是输出了，有好多类别，这里也不多说（容易造成理解和解释上的压力，一开始也未必能听懂，等于白讲），先看这个例子，只有一个Console，这些节点可不是随便命名的，Console就是输出控制台的意思。然后就针对这个输出设置一些属性，这里设置了PatternLayout就是输出格式了，基本上是前面时间，线程，级别，logger名称，log信息等，差不多，可以自己去查他们的语法规则。
        2）loggers下面会定义许多个logger，这些logger通过name进行区分，来对不同的logger配置不同的输出，方法是通过引用上面定义的logger，注意，appender-ref引用的值是上面每个appender的name，而不是节点名称。
        注意：loggers中logger的顺序
    这个例子为了说明什么呢？我们要说说这个logger的name（名称）了（前面有提到）。
    2）. name的机制：(可以参考： http://logging.apache.org/log4j/2.x/manual/architecture.html)
        <appenders>中appender的name是为了后面loggers中的logger元素引用的
        <loggers>中logger的name是为了package中属性做定义的。 



