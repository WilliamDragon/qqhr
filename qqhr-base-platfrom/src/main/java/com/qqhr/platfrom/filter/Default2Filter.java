package com.qqhr.platfrom.filter;

import com.qqhr.golden.trade.async.AsyncMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author WilliamDragon
 * @Date 2021/4/15 15:55
 * @Version 1.0
 */
@Component
public class Default2Filter extends AbstractFilter{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostConstruct
    protected void registerSefl() {
        super.registerFilter(this);
    }

    @Override
    public boolean doFilter(AsyncMessage asyncMessage) {
        log.info("进行过滤2");
        return true;
    }
}
