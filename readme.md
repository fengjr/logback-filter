## 1 项目介绍
---
   凤凰金融后端技术使用的主要是java语言，日志输出组件基本用的都是logback，orm框架用的是mybatis，为了更好的记录对数据库的操作日志，我们将mybatis的SQL执行过程单独打印到了一个文件。
   
   随着业务量的增长，某些应用每天打印的SQL日志已经达到好几百G，经过权衡，我们决定不再打印查询的日志，只打印增删改的SQL日志。
   
   由于项目众多，我们希望能找到一个通用的，并且能够通过更改类似logback配置的方法完成上述任务，
   
   经过调研发现，我们可以通过继承实现"ch.qos.logback.core.filter.Filter"类来达到我们的目的，最终诞生了本项目。
   
   logback-filter主要对logback中输出日志进行过滤，并保持了logback原汁原味的配置方式。
   
   目前支持 通过 ```前缀``` 和 ```正则表达式``` 两种方式过滤不想打印的日志。

## 2 依赖配置
---
   在使用logback-filter工具时，需要在pom.xml文件中引入dependency
```xml
    <dependency>
          <groupId>com.fengjr</groupId>
           <artifactId>fengjr-logback-filter</artifactId>
           <version>版本号</version>
    </dependency>
```
## 3 配置说明
---
  引入logback-filter依赖包后,在logback.xml进行配置就能完成日志过滤。  
  下面以debugFile日志输出为例,如果debug日志的输出方法以select、count或list开头,此条日志不会打印；如果日志输出内容包含one、two或three此条日志不会打印。
  
配置如下

```xml
<appender name="debugFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>D://logs/fengjr-logback-filter/debug.log</file>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>DEBUG</level>
        <onMatch>NEUTRAL</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
    <filter class="com.fengjr.logback.filter.IgnoreFilter">
        <ignorePolicy class="com.fengjr.logback.filter.IgnorePolicy">
            <methodPrefix class="com.fengjr.logback.filter.MethodPrefix">
                <array class="com.fengjr.logback.filter.Array">
                    <value>select</value>
                    <value>count</value>
                    <value>list</value>
                </array>
            </methodPrefix>
            <msgRegex class="com.fengjr.logback.filter.MsgRegex">
                <array class="com.fengjr.logback.filter.Array">
                    <value>.*(one).*</value>
                    <value>.*(two).*</value>
                    <value>.*(three).*</value>
                </array>
            </msgRegex>
        </ignorePolicy>
    </filter>
</appender>
```
配置步骤

  (1) 将前一个filter中的onMatch属性配置为NEUTRAL，即```<onMatch>NEUTRAL</onMatch> ``` 
  
  (2) 增加```<filter class="com.fengjr.logback.IgnoreFilter"></filter>```自定义过滤器标签  
  
  (3) 根据业务需求配置选择MethodPrefix(方法前缀过滤)和MsgRegex(日志内容正则过滤)
  
  (4) ```<methodPrefix><methodPrefix/>```里面的```<array></array>```配置的是方法的前缀，如果匹配则不打印该条日志  
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;```<msgRegex></msgRegex>```里面```<array></array>```配置的是日志内容的正则，如果匹配则不打印该条日志