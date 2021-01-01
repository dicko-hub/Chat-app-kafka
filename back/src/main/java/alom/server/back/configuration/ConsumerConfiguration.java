package alom.server.back.configuration;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerConfiguration {

	KafkaConsumer<String, String> consumer;

    public ConsumerConfiguration(String topic, String groupeId) {
    	
        this.consumer = new KafkaConsumer<String, String>(consumerConfigurations( groupeId));
        this.consumer.subscribe(Arrays.asList(topic));
    }

    public KafkaConsumer getConsumer() {
		return consumer;
	}
	
    public Properties consumerConfigurations(String groupeId) {
    	Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupeId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
           "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
           "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
