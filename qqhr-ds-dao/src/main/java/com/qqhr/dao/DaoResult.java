package com.qqhr.dao;

import com.qqhr.common.base.BaseResult;

/**
 * 原子业务动作结果类
 * todo 未完待续
 * @param <T>
 */
public class DaoResult<T> extends BaseResult {

    private boolean isDateExist = false;

    //todo 未完待续
    /*public DaoResult<T> success(T result){
        super.success(result);
        this.isDateExist = true;
        return this;
    }*/

    public boolean isDateExist() {
        return isDateExist;
    }

    public void setDateExist(boolean dateExist) {
        isDateExist = dateExist;
    }
}
