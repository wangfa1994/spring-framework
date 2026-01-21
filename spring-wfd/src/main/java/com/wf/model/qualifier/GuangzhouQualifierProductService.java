package com.wf.model.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier
public class GuangzhouQualifierProductService implements ProductService{
	@Override
	public String getInfo() {
		return "GuangzhouQualifier";
	}
}
