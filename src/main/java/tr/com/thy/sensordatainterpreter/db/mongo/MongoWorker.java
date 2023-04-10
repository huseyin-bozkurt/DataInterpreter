package tr.com.thy.sensordatainterpreter.db.mongo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import tr.com.thy.sensordatainterpreter.model.BatteryChange;
import tr.com.thy.sensordatainterpreter.model.IDataBaseWorkerExtension;
import tr.com.thy.sensordatainterpreter.model.InterpreterData;
import tr.com.thy.sensordatainterpreter.model.SensorData;

/**
 * The Class MongoWorker is used to do required queries on Mongo DB..
 */
@Component
@Qualifier("mongoWorker")
public class MongoWorker implements IDataBaseWorkerExtension {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MongoWorker.class);

	/** The sensor data mongo repository. */
	@Autowired
	private ISensorDataMongoRepository sensorDataMongoRepository;

	/** The sequence generator service. */
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	/** The battery change mongo repository. */
	@Autowired
	private IBatteryChangeMongoRepository batteryChangeMongoRepository;

	/**
	 * Save on Mongo.
	 *
	 * @param data the data
	 */
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void save(Object data) {
		try {
			if (data instanceof SensorData)
				sensorDataMongoRepository.save((SensorData) data);
			else if (data instanceof BatteryChange) {
				BatteryChange batteryChange = (BatteryChange) data;
				batteryChange.setId(sequenceGeneratorService.generateSequence(BatteryChange.SEQUENCE_NAME));
				batteryChangeMongoRepository.save((BatteryChange) data);
			}
		} catch (Exception e) {
			logger.error("Error occured during Save operation: {}", e);
		}
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @param clz the clz
	 * @return the interpreter data
	 */
	@Override
	@Transactional(readOnly = true)
	public InterpreterData findById(String id, Class<?> clz) {
		InterpreterData retVal = null;
		try {
			if (clz.equals(SensorData.class)) {
				Optional<SensorData> optionalSensorData = sensorDataMongoRepository.findById(id);
				if (optionalSensorData.isPresent()) {
					retVal = optionalSensorData.get();
				}
			} else if (clz.equals(BatteryChange.class)) {
				Optional<BatteryChange> optionalSensorData = batteryChangeMongoRepository.findById(id);
				if (optionalSensorData.isPresent()) {
					retVal = optionalSensorData.get();
				}
			}
		} catch (Exception e) {
			logger.error("Error occured during findById operation: {}", e);
		}
		return retVal;
	}

	/**
	 * Find by device id and event time.
	 *
	 * @param deviceId the device id
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SensorData> findByDeviceIdandEventTime(String deviceId, long startTime, long endTime) {
		List<SensorData> retVal = null;
		try {
			retVal = sensorDataMongoRepository.findByDeviceIdAndTime(deviceId, startTime, endTime);
		} catch (Exception e) {
			logger.error("Error occured during findByDeviceIdandEventTime operation: {}", e);
		}
		return retVal;
	}

}
