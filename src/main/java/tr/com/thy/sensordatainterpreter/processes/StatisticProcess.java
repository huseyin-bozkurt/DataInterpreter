package tr.com.thy.sensordatainterpreter.processes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.thy.sensordatainterpreter.model.IDataBaseWorkerExtension;
import tr.com.thy.sensordatainterpreter.model.InterpreterData;
import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.process.factory.EProcessType;
import tr.com.thy.sensordatainterpreter.process.factory.IProcessExtension;

/**
 * The Class StatisticProcess manages the Statistical Process life cycle..
 */
@Service
public class StatisticProcess implements IProcessExtension {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(StatisticProcess.class);

	/** The database. */
	@Autowired
	@Qualifier("mongoWorker")
	private IDataBaseWorkerExtension database;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@Override
	public EProcessType getType() {
		return EProcessType.STATISTICAL;
	}

	/**
	 * Save data.
	 *
	 * @param sensorData the sensor data
	 */
	@Override
	public void saveData(InterpreterData sensorData) {
		logger.debug("Save request for InterpreterData");
		database.save(sensorData);
	}

	/**
	 * Retrieve data.
	 *
	 * @param id  the id
	 * @param clz the clz
	 * @return the object
	 */
	@Override
	public Object retrieveData(String id, Class<?> clz) {
		logger.debug("retrieveData request for InterpreterData");
		return database.findById(id, clz);
	}

	/**
	 * Find by device id.
	 *
	 * @param deviceId  the device id
	 * @param startTime the start time
	 * @param endTime   the end time
	 * @return the list
	 */
	@Override
	public List<SensorData> findByDeviceId(String deviceId, long startTime, long endTime) {
		logger.debug("findByDeviceId request for InterpreterData");
		return database.findByDeviceIdandEventTime(deviceId, startTime, endTime);
	}

}
