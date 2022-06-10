package com.vitu.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface VendaProducer {

    @Topic("vendas")
    void produzirVenda(@KafkaKey String id, String vendaJson);

}
