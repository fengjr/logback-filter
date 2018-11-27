package com.fengjr.logback.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 根据<IgnorePolicy><IgnorePolicy/>的配置，对日志输出进行相应的过滤操作
 */
public class IgnoreFilter extends Filter<ILoggingEvent> {


    private IgnorePolicy ignorePolicy;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        //获取打印日志的方法名
        String[] logArray = event.getLoggerName().split("\\.");
        String methodName = logArray[logArray.length - 1];
        //1、方法前缀过滤
        MethodPrefix methodPrefix = ignorePolicy.getMethodPrefix();
        if (methodPrefix != null && methodPrefix.getArray() != null) {
            List<String> methodPrefixList = methodPrefix.getArray().getValues();
            for (String methodPrefixStr : methodPrefixList) {
                if (methodName.startsWith(methodPrefixStr)) {
                    return FilterReply.DENY;
                }
            }
        }
        //2、日志内容过滤
        String message = event.getMessage();
        MsgRegex msgRegex = ignorePolicy.getMsgRegex();
        if (msgRegex != null && msgRegex.getArray() != null) {
            List<String> msgRegexList = msgRegex.getArray().getValues();
            for (String msgRegexStr : msgRegexList) {
                if (Pattern.matches(msgRegexStr, message)) {
                    return FilterReply.DENY;
                }
            }
        }
        return FilterReply.NEUTRAL;

    }


    public IgnorePolicy getIgnorePolicy() {
        return ignorePolicy;
    }

    public void setIgnorePolicy(IgnorePolicy ignorePolicy) {
        this.ignorePolicy = ignorePolicy;
    }
}