package com.qqhr.platfrom.handler;

import com.qqhr.common.enums.TopicEnum;
import com.qqhr.golden.trade.async.AsyncMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author WilliamDragon
 * @Date 2021/4/19 14:02
 * @Version 1.0
 */
@Component
public class DefaultHandler extends AbstractHandler{

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostConstruct
    public void addHandler(){
        super.addHandler(TopicEnum.TRADE_JOB.gettopicName(),this);
    }

    /*public void removeHandler(){

    }*/
    @Override
    public void doHandle(AsyncMessage asyncMessage) {
        //处理消息todo
        log.info("选择器成功进入");
    }
}
