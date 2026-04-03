package com.babel.benjamin.empleados_app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "app.empleadosapp")
@Getter
@Setter
public class AppProps {
    private String MSG_ERROR_LIST_EMPTY;
}
