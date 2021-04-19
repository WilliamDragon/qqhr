package com.qqhr.platfrom.executor;

import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.platfrom.interfaces.IHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/4/19 14:57
 * @Version 1.0
 */
public class ExecutorPipeline {

    private List<IHandler> iHandlerList = Collections.synchronizedList(new ArrayList<>());

    public void handle(AsyncMessage asyncMessage){
        //此时filterList中 包括所有初始化的filter了已经
        this.iHandlerList.forEach(x->x.doHandle(asyncMessage));
    }

    public synchronized void addHandle(IHandler iHandler){
        this.iHandlerList.add(iHandler);
    }

    public synchronized void removeHandle(IHandler iHandler){
        this.iHandlerList.remove(iHandler);
    }

}
