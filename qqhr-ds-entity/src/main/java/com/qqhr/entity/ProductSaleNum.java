package com.qqhr.entity;

import java.io.Serializable;

/**
 * @Author WilliamDragon
 * @Date 2021/5/21 9:50
 * @Version 1.0
 */

public class ProductSaleNum implements Serializable {

    private static final long serialVersionUID = -3901168360650342628L;
    private String productId;//产品代码
    private Integer productSaleNum;//产品销售数量
    private Integer productSaleRemainNum;//产品剩余数量

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductSaleNum() {
        return productSaleNum;
    }

    public void setProductSaleNum(Integer productSaleNum) {
        this.productSaleNum = productSaleNum;
    }

    public Integer getProductSaleRemainNum() {
        return productSaleRemainNum;
    }

    public void setProductSaleRemainNum(Integer productSaleRemainNum) {
        this.productSaleRemainNum = productSaleRemainNum;
    }
}
