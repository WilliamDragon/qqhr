package com.qqhr.provider.sendService;

import com.qqhr.provider.dto.OrderDto;
import org.springframework.stereotype.Component;

/**
 * @Author WilliamDragon
 * @Date 2021/4/27 9:35
 * @Version 1.0
 */
@Component
public class MsgDispatcherSendOrders {

    public void execute(OrderDto orderDto){

        System.out.println(orderDto);
    }

}
