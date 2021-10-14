package com.manhpd.basicspringplugin;

import org.springframework.plugin.core.Plugin;

public interface WriterPlugin extends Plugin<String> {
    void write(String message);
}
