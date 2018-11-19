package com.fengjr.logback;

public class IgnorePolicy {
    private MethodPrefix methodPrefix;
    private MsgRegex msgRegex;
    public MethodPrefix getMethodPrefix() {
        return methodPrefix;
    }

    public void setMethodPrefix(MethodPrefix methodPrefix) {
        this.methodPrefix = methodPrefix;
    }

    public MsgRegex getMsgRegex() {
        return msgRegex;
    }

    public void setMsgRegex(MsgRegex msgRegex) {
        this.msgRegex = msgRegex;
    }
}
