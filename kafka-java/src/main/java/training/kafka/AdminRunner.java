package training.kafka;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.Collections;
import java.util.Properties;

public class AdminRunner {
    public static void main(String[] args) {
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                          "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9093");
        try (Admin adminLoc = Admin.create(propertiesLoc);) {
            short replication = 3;
            NewTopic newTopicLoc = new NewTopic("turkcell-first-topic",
                                                10,
                                                replication);
            CreateTopicsResult createTopicsResultLoc = adminLoc.createTopics(Collections.singleton(newTopicLoc));
            KafkaFuture<Void> voidKafkaFutureLoc = createTopicsResultLoc.values()
                                                                        .get("turkcell-first-topic");
            try {
                voidKafkaFutureLoc.get();
            } catch (Exception eParam) {
                eParam.printStackTrace();
            }
        }
    }
}
