package com.qqhr.platfrom.threadpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author WilliamDragon
 * @Date 2021/5/11 18:37
 * @Version 1.0
 */
//@Component
public class ThreadPollConfig {

    @Value("${spring.kafka.threadpool.coreSize}")
    protected int coreSize;

    @Value("${spring.kafka.threadpool.maxSize}")
    protected int maxSize;

    @Value("${spring.kafka.threadpool.keepAliveTime}")
    protected long keepAliveTime;

    @Value("${spring.kafka.threadpool.queueNum}")
    protected int queue_num;

    protected ConcurrentHashMap<String, ThreadPoolInfo> _multiThreadPoolInfo = new ConcurrentHashMap<>();

    //创建线程池 自定义参数
    public ThreadPoolInfo createThreadPoolInfo(String name){
        ThreadPoolInfo threadPoolInfo = registerThreadPoolInfo(name);
        return threadPoolInfo;
    }

    private ThreadPoolInfo registerThreadPoolInfo(String name){
        ThreadPoolInfo threadPoolInfo = new ThreadPoolInfo(name, coreSize, maxSize, keepAliveTime, queue_num);
        threadPoolInfo.setName(name);
        _multiThreadPoolInfo.put(threadPoolInfo.getName(),threadPoolInfo);
        return threadPoolInfo;
    }
    //创建默认线程池 从配置文件里面取
    public void init(){
        initConfig();
    }

    private void initConfig(){
        ThreadPoolInfo threadPoolInfo = new ThreadPoolInfo("DEFAULT_THREAD_POOL", coreSize, maxSize, keepAliveTime, queue_num);

        _multiThreadPoolInfo.put(threadPoolInfo.getName(),threadPoolInfo);
    }


}
