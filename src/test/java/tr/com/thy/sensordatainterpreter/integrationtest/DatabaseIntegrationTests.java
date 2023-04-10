package tr.com.thy.sensordatainterpreter.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tr.com.thy.sensordatainterpreter.db.h2.H2Worker;
import tr.com.thy.sensordatainterpreter.db.h2.IThresholdRepository;
import tr.com.thy.sensordatainterpreter.db.mongo.IBatteryChangeMongoRepository;
import tr.com.thy.sensordatainterpreter.db.mongo.ISensorDataMongoRepository;
import tr.com.thy.sensordatainterpreter.db.mongo.MongoWorker;
import tr.com.thy.sensordatainterpreter.db.mongo.SequenceGeneratorService;
import tr.com.thy.sensordatainterpreter.model.BatteryChange;
import tr.com.thy.sensordatainterpreter.model.EventLocation;
import tr.com.thy.sensordatainterpreter.model.Point;
import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.model.StatusChange;
import tr.com.thy.sensordatainterpreter.model.Threshold;

@SpringBootTest
public class DatabaseIntegrationTests {

	@Autowired
	private ISensorDataMongoRepository sensorDataMongoRepository;

	@Autowired
	private IBatteryChangeMongoRepository batteryChangeMongoRepository;

	@Autowired
	private IThresholdRepository thresholdRepository;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	@Autowired
	private H2Worker h2Database;

	@Autowired
	private MongoWorker mongoDatabase;

	@Autowired
	private SequenceGeneratorTestService sequenceGeneratorTestService;

	@Test
	void h2IntegrationTest() throws InterruptedException {
		Threshold actualThreshold = new Threshold("1234", 100, "unitTest");
		h2Database.save(actualThreshold);
		Threshold expectedThreshold = (Threshold) h2Database.findById(actualThreshold.getId(), Threshold.class);
		assertEquals(expectedThreshold.getId(), actualThreshold.getId());
		assertEquals(expectedThreshold.getThresholdValue(), actualThreshold.getThresholdValue());
		assertEquals(expectedThreshold.getType(), actualThreshold.getType());
		thresholdRepository.deleteById(expectedThreshold.getId());
	}

	@Test
	void mongoIntegrationTestSensorData() throws InterruptedException {
		List<String> propulsion_type = List.of("electric");
		List<Double> coordinates = List.of(-85.7865, 35.67576576678);
		Point geometry = new Point(coordinates);
		EventLocation eventLocation = new EventLocation(geometry);
		StatusChange statusChange = new StatusChange("56jddfg-44543-fgdfgs-353444353", "5679007", "TPS678",
				propulsion_type, "available", "user_drop_off", 15472345678L, eventLocation);
		SensorData actualSensorData = new SensorData("01", "HUD21", 26.09, 101573.00, 12.09, 45145.0, 12.09, 12.12,
				List.of(statusChange));
		mongoDatabase.save(actualSensorData);
		SensorData expectedSensorData = (SensorData) mongoDatabase.findById(actualSensorData.getId(), SensorData.class);
		assertEquals(expectedSensorData.toString(), actualSensorData.toString());
		sensorDataMongoRepository.deleteAll();
	}

	@Test
	void mongoIntegrationTestBatteryChange() throws InterruptedException {
		List<String> propulsion_type = List.of("electric");
		List<Double> coordinates = List.of(-85.7865, 35.67576576678);
		Point geometry = new Point(coordinates);
		EventLocation eventLocation = new EventLocation(geometry);
		StatusChange statusChange = new StatusChange("56jddfg-44543-fgdfgs-353444353", "5679007", "TPS678",
				propulsion_type, "available", "user_drop_off", 15472345678L, eventLocation);
		SensorData actualSensorData = new SensorData("01", "HUD21", 26.09, 101573.00, 12.09, 45145.0, 12.09, 12.12,
				List.of(statusChange));
		BatteryChange actualBatteryChange = new BatteryChange(false, "unitTest", actualSensorData);
		actualBatteryChange.setId(sequenceGenerator.generateSequence(BatteryChange.SEQUENCE_NAME));
		mongoDatabase.save(actualBatteryChange);
		BatteryChange expectedBatteryChange = (BatteryChange) mongoDatabase.findById(actualBatteryChange.getId(),
				BatteryChange.class);
		assertEquals(expectedBatteryChange.isBatteryChangedNeeded(), actualBatteryChange.isBatteryChangedNeeded());
		assertEquals(expectedBatteryChange.getType(), actualBatteryChange.getType());
		assertEquals(expectedBatteryChange.getSensorData().toString(), actualBatteryChange.getSensorData().toString());
		batteryChangeMongoRepository.deleteAll();
		sequenceGeneratorTestService.deleteSequence(BatteryChange.SEQUENCE_NAME);
	}

}
