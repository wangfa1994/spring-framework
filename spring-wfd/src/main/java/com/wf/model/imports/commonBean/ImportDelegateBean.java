package com.wf.model.imports.commonBean;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(CommonBean.class)
public class ImportDelegateBean {
}
