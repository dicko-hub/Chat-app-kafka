package alom.server.back.controlleur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import alom.server.back.configuration.ConsumerConfiguration;
import alom.server.back.configuration.ProducerConfiguration;
import alom.server.back.model.Demande;
import alom.server.back.model.Message;
import alom.server.back.model.MessagesList;


public class TopicController {

	
	private ProducerConfiguration producerConf = new ProducerConfiguration();
	private Producer<String, String> producer = producerConf.getProducer();

	
	public Boolean produce(Message msg) {
		msg.setTimestamp(LocalDateTime.now().toString());
		
		final ProducerRecord<String, String> recordProd = new ProducerRecord(msg.getTopic(), 
					msg.getSender(), msg.toString());
		Future<RecordMetadata> future = producer.send(recordProd);
		return true;
	}
	
	public MessagesList consume(Demande dmd) {
		dmd.setTimestamp(LocalDateTime.now().toString());
		ConsumerConfiguration consumerConf = new ConsumerConfiguration(dmd.getTopic(), dmd.getGroupeId());
		KafkaConsumer<String, String> consumer = consumerConf.getConsumer();
		ConsumerRecords<String, String> recordsCons = consumer.poll(1000);
		consumer.commitSync();
		consumer.close();
		ArrayList<String> messages = new ArrayList<String>();
		for (ConsumerRecord<String, String> recordCons : recordsCons) {
			messages.add(recordCons.value());
		 }
		return new MessagesList(messages);

	}
}
