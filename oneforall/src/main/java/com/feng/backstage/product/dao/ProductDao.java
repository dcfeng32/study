package com.feng.backstage.product.dao;

import com.feng.backstage.product.entity.Product;

/**
 * @author Administrator
 */
public interface ProductDao {

    /**
     * 通过PK获取产品信息
     * @param productKey
     * @return
     */
    Product findByProductKey(String productKey);

}
