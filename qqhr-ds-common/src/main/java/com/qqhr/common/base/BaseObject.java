package com.qqhr.common.base;

import com.qqhr.common.utils.JsonUtils;

import java.io.Serializable;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 15:51
 * @Version 1.0
 */

public class BaseObject implements Serializable {

    private static final long serialVersionUID = -2282423694035927117L;

    public String toString() {
        return this.toJson();
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }
}
