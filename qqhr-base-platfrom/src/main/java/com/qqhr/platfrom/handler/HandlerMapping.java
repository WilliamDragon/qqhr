package com.qqhr.platfrom.handler;

import com.qqhr.common.enums.TopicEnum;
import com.qqhr.platfrom.executor.ExecutorPipeline;
import com.qqhr.platfrom.interfaces.IHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * @Author WilliamDragon
 * @Date 2021/4/19 14:07
 * @Version 1.0
 */
@Component
public class HandlerMapping {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    ConcurrentHashMap<String, ExecutorPipeline> container = new ConcurrentHashMap<String, ExecutorPipeline>();

    public void addHandler(String messageType, IHandler handler){
        ExecutorPipeline executorPipeline = container.get(messageType);
        if(executorPipeline == null){
            executorPipeline = new ExecutorPipeline();
            container.put(messageType,executorPipeline);
        }
        executorPipeline.addHandle(handler);
    }

    public void removeHandler(String messageType, IHandler iHandler){
        ExecutorPipeline executorPipeline = container.get(messageType);
        if(executorPipeline == null){
            log.info("没有"+ messageType+ "对应的执行链");
        }
        executorPipeline.removeHandle(iHandler);
    }
    public ExecutorPipeline doSelectExecutorPipeline(String messageType){
        if(messageType != null){
           return container.get(messageType);
        }
        return null;
    }

}
