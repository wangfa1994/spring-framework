package com.wf.model.enable.impoortselect.config;

import com.wf.model.enable.impoortselect.EnableServer;
import com.wf.model.enable.impoortselect.ServerType;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableServer(type=ServerType.HTTP)
public class EnableConfig {
}
