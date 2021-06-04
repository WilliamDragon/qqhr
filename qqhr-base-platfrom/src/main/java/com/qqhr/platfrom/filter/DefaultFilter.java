package com.qqhr.platfrom.filter;

import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.platfrom.interfaces.IFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author WilliamDragon
 * @Date 2021/4/15 15:55
 * @Version 1.0
 * 以后增加过滤加继承AbstractFilte条件时候，可以增r类，eg:Default2Filter.java 一样，遵循开闭原则
 */
@Component
public class DefaultFilter extends AbstractFilter{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostConstruct
    protected void registerSefl() {
        super.registerFilter(this);
    }

    @Override
    public boolean doFilter(AsyncMessage asyncMessage) {
        log.info("进行过滤");
        return true;
    }
}
