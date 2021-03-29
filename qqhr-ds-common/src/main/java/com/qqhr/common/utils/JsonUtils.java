package com.qqhr.common.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author WilliamDragon
 * @Date 2021/3/17 15:53
 * @Version 1.0
 */

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    public static String toJson(Object object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            logger.warn("对象转换Json发生异常");
        }
        return null;
    }

    public static <T> T toObject (String json, Class<T> clazz){
        if (json != null && !json.isEmpty()){
            try {
                return OBJECT_MAPPER.readValue(json, clazz);
            } catch (Exception e) {
                logger.warn("对象转换Json发生异常");
            }
        }
        return null;
    }

}
