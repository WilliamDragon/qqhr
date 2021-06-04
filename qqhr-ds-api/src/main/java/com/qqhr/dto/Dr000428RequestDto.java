package com.qqhr.dto;

import com.qqhr.common.base.BaseResult;
import com.qqhr.golden.base.BaseRequestDto;

import java.io.Serializable;
import java.util.Date;

public class Dr000428RequestDto extends BaseRequestDto implements Serializable{

    private static final long serialVersionUID = -5775074079200280175L;
    private String productId;

    private String customerId;

    private String buyProductRemainamt;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBuyProductRemainamt() {
        return buyProductRemainamt;
    }

    public void setBuyProductRemainamt(String buyProductRemainamt) {
        this.buyProductRemainamt = buyProductRemainamt;
    }
}