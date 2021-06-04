package com.qqhr.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author WilliamDragon
 * @Date 2021/5/21 9:50
 * @Version 1.0
 */

public class ProductSaleShare implements Serializable {
    private static final long serialVersionUID = -5014350071399292826L;
    private String productId;//产品代码
    private String branchno;//产品所属机构
    private BigDecimal productAmt;//产品销售额度
    private BigDecimal productRemainamt;//产品剩余额度

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }

    public BigDecimal getProductAmt() {
        return productAmt;
    }

    public void setProductAmt(BigDecimal productAmt) {
        this.productAmt = productAmt;
    }

    public BigDecimal getProductRemainamt() {
        return productRemainamt;
    }

    public void setProductRemainamt(BigDecimal productRemainamt) {
        this.productRemainamt = productRemainamt;
    }
}
