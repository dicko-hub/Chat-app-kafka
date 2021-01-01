package alom.server.back.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;


public class ProducerConfiguration {
	
	Producer<String, String> producer;

    public ProducerConfiguration() {
    	
        this.producer = new KafkaProducer<String, String>(producerConfigurations());
    }
    
    public Producer getProducer() {
		return producer;
	}

	public Properties producerConfigurations() {
		
	      Properties props = new Properties();

	      // Assigner l'identifiant du serveur kafka
	      props.put("bootstrap.servers", "localhost:9092");

	      // Definir un acquittement pour les requetes du producteur
	      props.put("acks", "all");

	      // Si la requete echoue, le producteur peut reessayer automatiquemt
	      props.put("retries", 0);

	      // Specifier la taille du buffer size dans la config
	      props.put("batch.size", 16384);

	      // buffer.memory controle le montant total de memoire disponible au producteur pour le buffering
	      props.put("buffer.memory", 33554432);

	      props.put("key.serializer",
	         "org.apache.kafka.common.serialization.StringSerializer");

	      props.put("value.serializer",
	         "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

	    
}
