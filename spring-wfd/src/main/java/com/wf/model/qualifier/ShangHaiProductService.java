package com.wf.model.qualifier;

import org.springframework.stereotype.Service;

@Service
public class ShangHaiProductService implements ProductService{
	@Override
	public String getInfo() {
		return "shanghai";
	}
}
