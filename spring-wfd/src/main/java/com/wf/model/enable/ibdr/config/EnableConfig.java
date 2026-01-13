package com.wf.model.enable.ibdr.config;

import com.wf.model.enable.ibdr.EnableServer;
import com.wf.model.enable.ibdr.ServerType;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableServer(type= ServerType.HTTP)
public class EnableConfig {
}
