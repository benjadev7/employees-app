package com.babel.benjamin.empleados_app.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ AppProps.class })
public class AppConfig {
}
