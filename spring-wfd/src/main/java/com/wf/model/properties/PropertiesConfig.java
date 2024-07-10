package com.wf.model.properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@ComponentScan({"com.wf.model.properties"})
@PropertySource({"springConfig.properties"})
public class PropertiesConfig {
}
