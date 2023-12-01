package training.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerRunner {

    public static void main(String[] args) {
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                          "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9093");
        propertiesLoc.put(ProducerConfig.CLIENT_ID_CONFIG,
                          "client1");
        propertiesLoc.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                          IntegerSerializer.class.getName());
        propertiesLoc.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                          StringSerializer.class.getName());
        propertiesLoc.put(ProducerConfig.ACKS_CONFIG,
                          "1");

        Future<RecordMetadata> sendLoc = null;
        try (KafkaProducer<Integer, String> producerLoc = new KafkaProducer<>(propertiesLoc);) {
            for (int i = 0; i < 100; i++) {
                sendLoc = producerLoc.send(new ProducerRecord<>(AppConfig.topicName,
                                                                i,
                                                                "turkcell message " + i));
                System.out.println("Index : " + i);
            }
        }

        try {
            RecordMetadata recordMetadataLoc = sendLoc.get();
            System.out.println("Son data : topic : "
                               + recordMetadataLoc.topic()
                               + " partition : "
                               + recordMetadataLoc.partition()
                               + " offset : "
                               + recordMetadataLoc.offset());
        } catch (Exception eParam) {
            eParam.printStackTrace();
        }


    }
}
