package com.feng.backstage.product.service;

import com.feng.backstage.product.entity.Product;
import org.springframework.stereotype.Service;

/**
 * Create by east on 2019/11/6 14:22
 * @author Administrator
 */
@Service
public interface ProductService {

    /**
     * 通过产品PK，查找产品信息
     * @param productKey
     * @return
     */
    Product findOneByProductKey(String productKey);

}
