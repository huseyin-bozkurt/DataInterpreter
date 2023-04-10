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

import tr.com.thy.sensordatainterpreter.db.mongo.MongoWorker;
import tr.com.thy.sensordatainterpreter.model.EventLocation;
import tr.com.thy.sensordatainterpreter.model.Point;
import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.model.StatusChange;
import tr.com.thy.sensordatainterpreter.processes.StatisticProcess;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProcessStatisticUnitTests {

	@InjectMocks
	private StatisticProcess statisticProcess;

	@Mock
	private MongoWorker mongoDatabase;

	@Test
	void statisticProcessTest() {
		Mockito.doNothing().when(mongoDatabase).save(Mockito.any());
		statisticProcess.saveData(Mockito.any());

		List<String> propulsion_type = List.of("electric");
		List<Double> coordinates = List.of(-85.7865, 35.67576576678);
		Point geometry = new Point(coordinates);
		EventLocation eventLocation = new EventLocation(geometry);
		StatusChange statusChange = new StatusChange("56jddfg-44543-fgdfgs-353444353", "5679007", "TPS678",
				propulsion_type, "available", "user_drop_off", 15472345678L, eventLocation);
		SensorData sensorData = new SensorData("01", "HUD21", 26.09, 101573.00, 12.09, 45145.0, 12.09, 12.12,
				List.of(statusChange));
		Mockito.when(mongoDatabase.findById(Mockito.any(), Mockito.any())).thenReturn(sensorData);
		SensorData expectedSensorData = (SensorData) statisticProcess.retrieveData(sensorData.getId(),
				SensorData.class);
		assertEquals(expectedSensorData.toString(), sensorData.toString());

		List<SensorData> sensorDataList = List.of(sensorData);
		Mockito.when(mongoDatabase.findByDeviceIdandEventTime(Mockito.any(), Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(sensorDataList);
		List<SensorData> expectedSensorDataList = statisticProcess.findByDeviceId("56jddfg-44543-fgdfgs-353444353", 0,
				15472345679L);
		expectedSensorDataList.forEach(sd -> assertEquals(sd.toString(), sensorData.toString()));
	}

}
