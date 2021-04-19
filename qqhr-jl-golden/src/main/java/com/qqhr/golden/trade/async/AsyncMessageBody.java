package com.qqhr.golden.trade.async;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 16:15
 * @Version 1.0
 */

public class AsyncMessageBody {
    private String dataClass;
    private String data;

    public String getDataClass() {
        return dataClass;
    }

    public void setDataClass(String dataClass) {
        this.dataClass = dataClass;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
