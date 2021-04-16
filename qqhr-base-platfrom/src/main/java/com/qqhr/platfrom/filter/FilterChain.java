package com.qqhr.platfrom.filter;

import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.platfrom.interfaces.IFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/4/15 15:57
 * @Version 1.0
 */
@Component
public class FilterChain {
    private List<IFilter> filterList = Collections.synchronizedList(new ArrayList<IFilter>());

    public void filter(AsyncMessage asyncMessage){
        //此时filterList中 包括所有初始化的filter了已经
        this.filterList.forEach(x->x.doFilter(asyncMessage));
    }
    public void addFilter(IFilter iFilter){
        this.filterList.add(iFilter);
    }
    public void removeFilter(IFilter iFilter){
        this.filterList.remove(iFilter);
    }
}
