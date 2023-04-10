package tr.com.thy.sensordatainterpreter.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import tr.com.thy.sensordatainterpreter.model.InterpreterData;

/**
 * The Class KafkaConsumerConfig is used to configure the Kafka.
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	/** The kafka server. */
	@Value("${kafkaServer}")
	private String kafkaServer;

	/** The kafka group id. */
	@Value("${kafkaGroupId}")
	private String kafkaGroupId;

	/**
	 * Sensor data consumer.
	 *
	 * @return the consumer factory
	 */
	@Bean
	public ConsumerFactory<String, InterpreterData> sensorDataConsumer() {
		Map<String, Object> map = new HashMap<>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(),
				new JsonDeserializer<>(InterpreterData.class));
	}

	/**
	 * Sensor data listener.
	 *
	 * @return the concurrent kafka listener container factory
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, InterpreterData> sensorDataListener() {
		ConcurrentKafkaListenerContainerFactory<String, InterpreterData> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(sensorDataConsumer());
		return factory;
	}

}
