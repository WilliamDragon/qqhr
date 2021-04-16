package com.qqhr.platfrom.interfaces;

import com.qqhr.golden.trade.async.AsyncMessage;

/**
 * @Author WilliamDragon
 * @Date 2021/4/15 15:50
 * @Version 1.0
 */

public interface IFilter {
    public boolean doFilter(AsyncMessage asyncMessage);
}
