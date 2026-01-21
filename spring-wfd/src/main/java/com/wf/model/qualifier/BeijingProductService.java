package com.wf.model.qualifier;

import org.springframework.stereotype.Service;

@Service
public class BeijingProductService implements ProductService {


	@Override
	public String getInfo() {
		return "beijing";
	}
}
