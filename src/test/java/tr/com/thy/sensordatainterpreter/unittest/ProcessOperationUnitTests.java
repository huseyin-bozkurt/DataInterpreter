package tr.com.thy.sensordatainterpreter.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tr.com.thy.sensordatainterpreter.db.h2.H2Worker;
import tr.com.thy.sensordatainterpreter.db.mongo.MongoWorker;
import tr.com.thy.sensordatainterpreter.model.EventLocation;
import tr.com.thy.sensordatainterpreter.model.Point;
import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.model.StatusChange;
import tr.com.thy.sensordatainterpreter.model.Threshold;
import tr.com.thy.sensordatainterpreter.processes.OperationProcess;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProcessOperationUnitTests {

	@InjectMocks
	private OperationProcess operationProcess;

	@Mock
	private MongoWorker mongoDatabase;

	@Mock
	private H2Worker h2Database;

	@Test
	void operationalProcessTest() {
		Threshold actualThreshold = new Threshold("01", 1000, "unitTest");
		Mockito.when(h2Database.findById(Mockito.any(), Mockito.any())).thenReturn(actualThreshold);
		Threshold expectedThreshold = (Threshold) operationProcess.retrieveData(actualThreshold.getId(),
				Threshold.class);
		assertEquals(expectedThreshold.getId(), actualThreshold.getId());
		assertEquals(expectedThreshold.getThresholdValue(), actualThreshold.getThresholdValue());
		assertEquals(expectedThreshold.getType(), actualThreshold.getType());

		List<String> propulsion_type = List.of("electric");
		List<Double> coordinates = List.of(-85.7865, 35.67576576678);
		Point geometry = new Point(coordinates);
		EventLocation eventLocation = new EventLocation(geometry);
		StatusChange statusChange = new StatusChange("56jddfg-44543-fgdfgs-353444353", "5679007", "TPS678",
				propulsion_type, "available", "user_drop_off", 15472345678L, eventLocation);
		SensorData sensorData = new SensorData("01", "HUD21", 26.09, 101573.00, 12.09, 45145.0, 12.09, 12.12,
				List.of(statusChange));
		Mockito.doNothing().when(mongoDatabase).save(Mockito.any());
		operationProcess.saveData(sensorData);
	}

}
