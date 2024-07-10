package com.wf.model.annxml;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages ={"com.wf.model.annxml.ann"})
@ImportResource(locations={"beanAnnXml.xml"})
@PropertySource("")
public class AnnXmlConfig {
}
