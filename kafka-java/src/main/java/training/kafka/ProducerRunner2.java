package training.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerRunner2 {

    public static void main(String[] args) throws Exception {
        Properties propertiesLoc = new Properties();
        Path       pathLoc       = Paths.get(".");
        System.out.println(pathLoc.toAbsolutePath().toString());

        propertiesLoc.load(Files.newInputStream(new File("./kafka-java/src/main/resources/producer.properties").toPath()));

        Future<RecordMetadata> sendLoc = null;
        try (KafkaProducer<Integer, String> producerLoc = new KafkaProducer<>(propertiesLoc);) {
            for (int i = 0; i < 1_000; i++) {
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
