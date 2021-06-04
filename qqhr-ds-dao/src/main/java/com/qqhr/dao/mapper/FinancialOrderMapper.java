package com.qqhr.dao.mapper;



import com.qqhr.entity.FinancialOrder;
import com.qqhr.entity.ProductSaleShare;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FinancialOrderMapper {


    int insertFinancialOrder(FinancialOrder financialOrder);

}