package com.qqhr.dao.mapper;



import com.qqhr.po.KafkaMessageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface kafkaMessageInfoMapper {
    int insert(KafkaMessageInfo kafkaMessageInfo);

    List<KafkaMessageInfo> findListMessageInfo();

}