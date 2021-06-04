package com.qqhr.provider.impl;

import com.qqhr.api.Dr000428BuyService;
import com.qqhr.common.utils.R;
import com.qqhr.common.utils.StringUtil;
import com.qqhr.dao.mapper.FinancialOrderMapper;
import com.qqhr.dao.mapper.ProductSaleNumMapper;
import com.qqhr.dao.mapper.ProductSaleShareMapper;
import com.qqhr.dto.Dr000428RequestDto;
import com.qqhr.entity.FinancialOrder;
import com.qqhr.entity.ProductSaleShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WilliamDragon
 * @Date 2021/5/21 10:34
 * @Version 1.0
 */
@Service
public class Dr000428BuyServiceImpl implements Dr000428BuyService {

    @Autowired
    private ProductSaleShareMapper productSaleShareMapper;//剩余额度
    @Autowired
    private ProductSaleNumMapper productSaleNumMapper;//剩余数量
    @Autowired
    private FinancialOrderMapper financialOrderMapper;//产品订单
    @Override
    /*@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public R execute(Map param) {
       *//* try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        String productId = (String)param.get("productId");
        BigDecimal buyProductRemainamt = new BigDecimal((String)param.get("buyProductRemainamt"));
        //检查产品剩余额度
        ProductSaleShare productSaleShare = productSaleShareMapper.findProductSaleShare(productId);
        BigDecimal productRemainamt = new BigDecimal(0);
        if(productSaleShare != null){
            productRemainamt = productSaleShare.getProductRemainamt();
            if(productRemainamt != null){
                if(productRemainamt.compareTo(BigDecimal.ZERO) <= 0){
                    return R.error("额度不足");
                }
            }
        }
        //产品扣减额度
        BigDecimal productRemainamtResult = productRemainamt.subtract(buyProductRemainamt);
        if(productRemainamtResult.compareTo(BigDecimal.ZERO) < 0){
            return R.error("产品额度不足");
        }
        ProductSaleShare productSaleShare1 = new ProductSaleShare();
        productSaleShare1.setProductId(productId);
        productSaleShare1.setProductRemainamt(productRemainamtResult);
        productSaleShareMapper.updateProductSaleShare(productSaleShare1);
        //生成订单

        FinancialOrder financialOrder = new FinancialOrder();
        String customerId = (String)param.get("customerId");
        String uuid = UUID.randomUUID().toString();
        financialOrder.setOrderId(uuid);
        financialOrder.setCustomerId(customerId);
        financialOrder.setProductId(productId);
        financialOrder.setBuyAmt(buyProductRemainamt);
        financialOrder.setBuyNum(1);
        financialOrder.setBuyDate(new Date());
        financialOrderMapper.insertFinancialOrder(financialOrder); *//*
        return R.ok();
    }*/

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public R execute(Dr000428RequestDto dr00428RequestDto) {
        /*try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        String productId = dr00428RequestDto.getProductId();
        String customerId = dr00428RequestDto.getCustomerId();
        BigDecimal buyProductRemainamt = new BigDecimal((String)dr00428RequestDto.getBuyProductRemainamt());
        //String productId = (String)param.get("productId");
        //BigDecimal buyProductRemainamt = new BigDecima((String)param.get("bulyProductRemainamt"));
        //检查产品剩余额度
        ProductSaleShare productSaleShare = productSaleShareMapper.findProductSaleShare(productId);
        BigDecimal productRemainamt = new BigDecimal(0);
        if(productSaleShare != null){
            productRemainamt = productSaleShare.getProductRemainamt();
            if(productRemainamt != null){
                if(productRemainamt.compareTo(BigDecimal.ZERO) <= 0){
                    return R.error("额度不足");
                }
            }
        }
        //产品扣减额度
        BigDecimal productRemainamtResult = productRemainamt.subtract(buyProductRemainamt);
        if(productRemainamtResult.compareTo(BigDecimal.ZERO) < 0){
            return R.error("产品额度不足");
        }
        ProductSaleShare productSaleShare1 = new ProductSaleShare();
        productSaleShare1.setProductId(productId);
        productSaleShare1.setProductRemainamt(productRemainamtResult);
        productSaleShareMapper.updateProductSaleShare(productSaleShare1);
        //生成订单

        FinancialOrder financialOrder = new FinancialOrder();
        String uuid = UUID.randomUUID().toString();
        financialOrder.setOrderId(uuid);
        financialOrder.setCustomerId(customerId);
        financialOrder.setProductId(productId);
        financialOrder.setBuyAmt(buyProductRemainamt);
        financialOrder.setBuyNum(1);
        financialOrder.setBuyDate(new Date());
        financialOrderMapper.insertFinancialOrder(financialOrder);
        return R.ok();
    }
}
