package com.qqhr.platfrom.dto;

import java.io.Serializable;

/**
 * @Author WilliamDragon
 * @Date 2021/2/1 9:49
 * @Version 1.0
 */

public class KafkaMessage implements Serializable {
    KafkaMeaasgeHead kafkaMeaasgeHead;
    KafkaMessageBody kafkaMessageBody;

    public KafkaMeaasgeHead getKafkaMeaasgeHead() {
        return kafkaMeaasgeHead;
    }

    public void setKafkaMeaasgeHead(KafkaMeaasgeHead kafkaMeaasgeHead) {
        this.kafkaMeaasgeHead = kafkaMeaasgeHead;
    }

    public KafkaMessageBody getKafkaMessageBody() {
        return kafkaMessageBody;
    }

    public void setKafkaMessageBody(KafkaMessageBody kafkaMessageBody) {
        this.kafkaMessageBody = kafkaMessageBody;
    }
}
