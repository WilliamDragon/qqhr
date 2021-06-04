package com.qqhr.dao.mapper;



import com.qqhr.entity.ProductSaleShare;
import com.qqhr.po.KafkaMessageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSaleShareMapper {

    ProductSaleShare findProductSaleShare(String productId);

    int updateProductSaleShare(ProductSaleShare productSaleShare);

}