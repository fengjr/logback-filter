## 1 项目介绍

   fengjr-logback-filter主要对logback中输出日志进行过滤，以便节省磁盘空间。目前支持打印日志的方法前缀过滤和日志内容的正则过滤两种方式。
    
## 2 依赖配置

   在使用fengjr-logback-filter工具时，需要在pom.xml文件中引入dependency
```xml
<dependency>
      <groupId>com.fengjr</groupId>
       <artifactId>fengjr-logback-filter</artifactId>
       <version>版本号</version>
</dependency>
```
## 3 配置说明

   引入fengjr-logback-filter依赖包后,在logback.xml进行配置就能完成日志过滤。
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

