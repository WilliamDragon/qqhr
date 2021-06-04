package com.qqhr.provider.msgdispatcher;

import com.google.gson.Gson;
import com.qqhr.common.utils.JsonUtils;
import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.golden.trade.async.AsyncMessageBuilder;
import com.qqhr.golden.trade.async.AsyncMessageRequest;
import com.qqhr.platfrom.dispatcher.MsgDispatcher;
import com.qqhr.platfrom.dto.KafkaMessage;
import com.qqhr.platfrom.service.KafkaSendTemplate;
import com.qqhr.provider.dto.OrderDto;
import com.qqhr.provider.sendService.MsgDispatcherSendOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/4/27 9:31
 * @Version 1.0
 */
@RestController
public class MsgController {

    @Autowired
    private MsgDispatcher msgDispatcher;

    @Autowired
    private KafkaSendTemplate kafkaSendTemplate;
    @PostMapping("/testMsgDispatcher")
    public void testMsgDispatcher() throws IllegalAccessException, ClassNotFoundException {


        List<OrderDto> orderDtos = new ArrayList<>();
        for(int i=1;i<50;i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setName(String.valueOf(i));
            orderDto.setAge(String.valueOf(i));
            orderDtos.add(orderDto);
        }
        AsyncMessage asyncMessage = AsyncMessageBuilder.newRequest()
                .withproducerClass(MsgController.class)
                .withconsumerClass(MsgDispatcherSendOrders.class)
                .withconsumerMethod()
                .withconsumerRequestDto(orderDtos.get(0))
                .build();

        //发送kafka
        kafkaSendTemplate.kaSendTemplate("guojinlongTopic",asyncMessage);

        /*String bodyData = asyncMessage.getAsyncMessageBody().getData();
        String bodyDataClass =  asyncMessage.getAsyncMessageBody().getDataClass();
        AsyncMessageRequest asyncMessageRequest = (AsyncMessageRequest) JsonUtils.toObject(bodyData, Class.forName(bodyDataClass));

        msgDispatcher.dispatcher(asyncMessageRequest);*/

    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        List<OrderDto> orderDtos = new ArrayList<>();
        for(int i=1;i<50;i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setName(String.valueOf(i));
            orderDto.setAge(String.valueOf(i));
            orderDtos.add(orderDto);
        }
        AsyncMessage asyncMessage = AsyncMessageBuilder.newRequest()
                .withproducerClass(MsgController.class)
                .withconsumerClass(MsgDispatcherSendOrders.class)
                .withconsumerMethod()
                .withconsumerRequestDto(orderDtos.get(0))
                .build();

        Gson gson = new Gson();
        String s = gson.toJson(asyncMessage);
        System.out.println(s);
        /*AsyncMessage asyncMessage1 = gson.fromJson(s, AsyncMessage.class);
        System.out.println(asyncMessage1);

        String bodyData = asyncMessage.getAsyncMessageBody().getData();
        String bodyDataClass =  asyncMessage.getAsyncMessageBody().getDataClass();
        AsyncMessageRequest asyncMessageRequest = (AsyncMessageRequest) JsonUtils.toObject(bodyData, Class.forName(bodyDataClass));
*/
        //System.out.println(asyncMessageRequest);

    }

}
