package training.kafka;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;

public class App
{
    public static void main( String[] args )
    {
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9093");
        Admin adminLoc = Admin.create(propertiesLoc);

    }
}
