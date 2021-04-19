package com.qqhr.platfrom.handler;

import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.platfrom.interfaces.IHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WilliamDragon
 * @Date 2021/4/19 13:59
 * @Version 1.0
 */
@Component
public abstract class AbstractHandler implements IHandler<AsyncMessage> {

    @Autowired
    private HandlerMapping handlerMapping;
    public void addHandler(String messageType, IHandler iHandler){
        handlerMapping.addHandler(messageType,iHandler);
    }
    public void removeHandler(String messageType, IHandler iHandler){
        handlerMapping.removeHandler(messageType,iHandler);
    }

}
