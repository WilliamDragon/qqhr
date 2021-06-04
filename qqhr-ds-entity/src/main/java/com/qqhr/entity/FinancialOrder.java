package com.qqhr.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author WilliamDragon
 * @Date 2021/5/21 10:54
 * @Version 1.0
 */

public class FinancialOrder implements Serializable {

    private static final long serialVersionUID = -4228773104618539771L;
    private String orderId;//订单ID
    private String customerId;//客户ID
    private String productId;//产品代码
    private BigDecimal buyAmt;//购买金额
    private Integer buyNum;//购买数量
    private Date buyDate;//购买时间

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getBuyAmt() {
        return buyAmt;
    }

    public void setBuyAmt(BigDecimal buyAmt) {
        this.buyAmt = buyAmt;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}
