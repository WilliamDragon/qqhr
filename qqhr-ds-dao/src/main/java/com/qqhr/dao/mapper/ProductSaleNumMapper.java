package com.qqhr.dao.mapper;



import com.qqhr.entity.ProductSaleNum;
import com.qqhr.entity.ProductSaleShare;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductSaleNumMapper {

    ProductSaleNum findProductSaleNum(String productId);

    int updateProductSaleNum(ProductSaleNum productSaleNum);

}