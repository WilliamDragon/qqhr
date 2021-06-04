package com.qqhr.provider.impl;

import com.qqhr.api.Dr000428ChangeBuyService;
import com.qqhr.common.utils.R;
import com.qqhr.dto.Dr000428RequestDto;
import com.qqhr.golden.trade.async.AsyncMessage;
import com.qqhr.golden.trade.async.AsyncMessageBuilder;
import com.qqhr.platfrom.service.KafkaSendTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WilliamDragon
 * @Date 2021/6/2 11:19
 * @Version 1.0
 */
@Service
public class Dr000428ChangeBuyServiceImpl implements Dr000428ChangeBuyService {
    @Autowired
    private KafkaSendTemplate kafkaSendTemplate;
    @Override
    public R execute(Dr000428RequestDto dr00428RequestDto) throws IllegalAccessException {

        AsyncMessage asyncMessage = AsyncMessageBuilder.newRequest()
                .withproducerClass(Dr000428ChangeBuyServiceImpl.class)
                .withconsumerClass(Dr000428BuyServiceImpl.class)
                .withconsumerMethod()
                .withconsumerRequestDto(dr00428RequestDto)
                .build();
        kafkaSendTemplate.kaSendTemplate("guojinlongTopic",asyncMessage);

        return R.ok();
    }
}
