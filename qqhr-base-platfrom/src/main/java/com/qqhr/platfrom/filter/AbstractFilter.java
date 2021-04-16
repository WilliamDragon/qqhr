package com.qqhr.platfrom.filter;

import com.qqhr.platfrom.interfaces.IFilter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author WilliamDragon
 * @Date 2021/4/15 15:52
 * @Version 1.0
 */

public  abstract class AbstractFilter implements IFilter {

    @Autowired
    private FilterChain filterChain;
    //注册Filter
    protected void registerFilter(IFilter iFilter){
        filterChain.addFilter(iFilter);
    }
    //移除Filter
    protected void removeFilter(IFilter iFilter){
        filterChain.removeFilter(iFilter);
    }

}
