package com.qqhr.golden.trade.async;

import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 16:15
 * @Version 1.0
 */

public class AsyncMessageBody<E> {
    private String dataClass;
    private String data;

    private List<E> itemlist;

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

    public List<E> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<E> itemlist) {
        this.itemlist = itemlist;
    }
}
