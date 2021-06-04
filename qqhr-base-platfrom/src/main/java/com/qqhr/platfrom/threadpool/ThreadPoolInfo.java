package com.qqhr.platfrom.threadpool;

/**
 * @Author WilliamDragon
 * @Date 2021/5/11 18:33
 * @Version 1.0
 */

public class ThreadPoolInfo {
    private String name;//线程池名称
    private int coreSize;//核心线程数
    private int maxSize;//最大线程数
    private long threadKeepAliveTime;
    private int queueSize;//队列数量

    public ThreadPoolInfo(String name, int coreSize, int maxSize, long threadKeepAliveTime, int queueSize) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.threadKeepAliveTime = threadKeepAliveTime;
        this.queueSize = queueSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public long getThreadKeepAliveTime() {
        return threadKeepAliveTime;
    }

    public void setThreadKeepAliveTime(long threadKeepAliveTime) {
        this.threadKeepAliveTime = threadKeepAliveTime;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }
}
