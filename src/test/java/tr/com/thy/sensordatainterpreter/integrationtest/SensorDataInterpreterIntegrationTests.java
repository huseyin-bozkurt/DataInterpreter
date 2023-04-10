package tr.com.thy.sensordatainterpreter.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import tr.com.thy.sensordatainterpreter.db.mongo.IBatteryChangeMongoRepository;
import tr.com.thy.sensordatainterpreter.db.mongo.ISensorDataMongoRepository;
import tr.com.thy.sensordatainterpreter.db.mongo.MongoWorker;
import tr.com.thy.sensordatainterpreter.model.BatteryChange;
import tr.com.thy.sensordatainterpreter.model.EventLocation;
import tr.com.thy.sensordatainterpreter.model.Point;
import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.model.StatusChange;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://127.0.0.1:9092", "port=9092" })
class SensorDataInterpreterIntegrationTests {

	@Autowired
	public KafkaTemplate<String, SensorData> template;

	@Autowired
	public MongoWorker mongoWorker;

	@Autowired
	private ISensorDataMongoRepository sensorDataMongoRepository;

	@Autowired
	private IBatteryChangeMongoRepository batteryChangeMongoRepository;

	@Autowired
	private SequenceGeneratorTestService sequenceGeneratorTestService;

	@Test
	void fullCycleTest() throws InterruptedException {
		List<String> propulsion_type = List.of("electric");
		List<Double> coordinates = List.of(-85.7865, 35.67576576678);
		Point geometry = new Point(coordinates);
		EventLocation eventLocation = new EventLocation(geometry);
		StatusChange statusChange = new StatusChange("56jddfg-44543-fgdfgs-353444353", "5679007", "TPS678",
				propulsion_type, "available", "user_drop_off", 15472345678L, eventLocation);
		SensorData actualSensorData = new SensorData("01", "HUD21", 26.09, 101573.00, 12.09, 45145.0, 12.09, 12.12,
				List.of(statusChange));
		Thread.sleep(10000);
		template.send("kafkaTopic", actualSensorData);
		Thread.sleep(2000);
		SensorData expectedSensorData = (SensorData) mongoWorker.findById(actualSensorData.getId(), SensorData.class);
		assertEquals(expectedSensorData.toString(), actualSensorData.toString());

		List<SensorData> expectedSensorDataList = mongoWorker
				.findByDeviceIdandEventTime("56jddfg-44543-fgdfgs-353444353", 0, 15472345679L);
		assertEquals(expectedSensorDataList.get(0).toString(), actualSensorData.toString());
		sensorDataMongoRepository.deleteAll();
		batteryChangeMongoRepository.deleteAll();
		sequenceGeneratorTestService.deleteSequence(BatteryChange.SEQUENCE_NAME);
		Thread.sleep(1000);
	}

}
