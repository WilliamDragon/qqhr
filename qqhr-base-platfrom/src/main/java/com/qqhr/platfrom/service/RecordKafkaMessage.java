package com.qqhr.platfrom.service;

import com.qqhr.dao.mapper.kafkaMessageInfoMapper;
import com.qqhr.po.KafkaMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author WilliamDragon
 * @Date 2021/6/3 14:35
 * @Version 1.0
 */
@Service
public class RecordKafkaMessage {
    private static final Logger log= LoggerFactory.getLogger(RecordKafkaMessage.class);

    @Autowired
    private kafkaMessageInfoMapper kafkaMessageInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertKafkaMessage(KafkaMessageInfo kafkaMessageInfo){

        List<KafkaMessageInfo> listMessageInfo = kafkaMessageInfoMapper.findListMessageInfo();

        kafkaMessageInfo.setTopic("asdf");
        kafkaMessageInfoMapper.updateKafkaMessageInfo(kafkaMessageInfo);
        int insert = kafkaMessageInfoMapper.insert(kafkaMessageInfo);


        System.out.println(insert);
        log.info("=========kafka发送数据成功(日志结束)=========");
    }
}
