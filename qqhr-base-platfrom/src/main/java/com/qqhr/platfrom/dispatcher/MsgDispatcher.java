package com.qqhr.platfrom.dispatcher;

import com.qqhr.common.utils.JsonUtils;
import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.golden.trade.async.AsyncMessageRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/4/27 10:50
 * @Version 1.0
 */
@Component
public class MsgDispatcher implements ApplicationContextAware {

    private ApplicationContext context;

    public void dispatcher(AsyncMessageRequest asyncMessageRequest){
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class serviceClass = classLoader.loadClass(asyncMessageRequest.getConsumerClass());
            //Class methodClass = classLoader.loadClass(asyncMessageRequest.getConsumerMethod());
            Object service = context.getBean(serviceClass);

            //============
            Class consumerRequestDtoClass = classLoader.loadClass(asyncMessageRequest.getConsumerRequestDtoClass());
            Object request = JsonUtils.toObject(asyncMessageRequest.getGetConsumerRequestDto(), consumerRequestDtoClass.newInstance().getClass());
            //==================

            Method method = serviceClass.getMethod(asyncMessageRequest.getConsumerMethod(),consumerRequestDtoClass);

            method.invoke(service, request);
            //requestlist.parallelStream().forEach(x->doTran(x));


        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
