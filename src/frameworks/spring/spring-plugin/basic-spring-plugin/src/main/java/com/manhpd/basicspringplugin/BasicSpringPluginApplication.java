package com.manhpd.basicspringplugin;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@SpringBootApplication
@EnablePluginRegistries(WriterPlugin.class)
public class BasicSpringPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringPluginApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(PluginRegistry<WriterPlugin, String> plugins) {
        return args -> {
            for (String format : "csv,txt".split(",")) {
                plugins.getPluginFor(format).get().write("Hello, Spring plugin!");
            }
        };
    }

}
