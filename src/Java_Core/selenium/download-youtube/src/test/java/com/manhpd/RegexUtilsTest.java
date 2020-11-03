package com.manhpd;

import junit.framework.TestCase;

import java.util.Set;

public class RegexUtilsTest extends TestCase {

    public void testExtractVideoId() {
        String text = "/watch?v=xpDnVSmNFX0&list=PLMCXHnjXnTnvo6alSjVkgxV-VH6EPyvoX&index=1&t=2s";
        Set<String> results = RegexUtils.extractVideoIds(text);
    }
}