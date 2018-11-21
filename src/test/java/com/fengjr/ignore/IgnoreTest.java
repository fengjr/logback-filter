package com.fengjr.ignore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class IgnoreTest {
    Logger logger = LoggerFactory.getLogger(IgnoreTest.class);

    @Test
    public void seletHello() {
        logger.debug("测试-selectHello one");
    }

    @Test
    public void testHello() {
        logger.debug(String.format("测试-testHello1 1ne %s", "Mm"));
    }

    @Test
    public void testRegex() {
        String str = "1-one-1";
        System.out.println(Pattern.matches(".*(one).*", str));
    }
}
