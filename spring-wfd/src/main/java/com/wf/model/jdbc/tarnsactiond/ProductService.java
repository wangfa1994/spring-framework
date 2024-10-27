package com.wf.model.jdbc.tarnsactiond;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateProductStockCountById(Integer stockCount, Long id){
		productDao.updateProductStockCountById(stockCount, id);
		int i = 1 / 0;
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateProductStockCountById2(Integer stockCount, Long id){
		productDao.updateProductStockCountById(stockCount, id);

	}

}
