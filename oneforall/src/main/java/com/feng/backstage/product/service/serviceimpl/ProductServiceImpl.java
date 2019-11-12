package com.feng.backstage.product.service.serviceimpl;

import com.feng.backstage.product.dao.ProductDao;
import com.feng.backstage.product.entity.Product;
import com.feng.backstage.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by east on 2019/11/6 14:23
 * @author Administrator
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findOneByProductKey(String productKey) {
        return productDao.findByProductKey(productKey);
    }

}
