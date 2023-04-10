package tr.com.thy.sensordatainterpreter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import tr.com.thy.sensordatainterpreter.model.InterpreterData;
import tr.com.thy.sensordatainterpreter.process.factory.ProcessFactory;

/**
 * The Class KafkaConsumerService is the consumer of the queue.
 */
@Service
public class KafkaConsumerService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	/**
	 * Consume the queue.
	 *
	 * @param data the data
	 */
	@KafkaListener(topics = "kafkaTopic", groupId = "id", containerFactory = "sensorDataListener")
	public void consume(InterpreterData data) {
		try {
			logger.debug("Consumed message on queue: {}", data.toString());
			ProcessFactory.getProcessMap().keySet().forEach(k -> ProcessFactory.getProcess(k).saveData(data));
		} catch (Exception e) {
			logger.error("Error occured when consuming the message from queue: {}", e);
		}
	}

}
