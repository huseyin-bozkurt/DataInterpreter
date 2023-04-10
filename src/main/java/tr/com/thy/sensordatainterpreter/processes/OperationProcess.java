package tr.com.thy.sensordatainterpreter.processes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.thy.sensordatainterpreter.model.BatteryChange;
import tr.com.thy.sensordatainterpreter.model.IDataBaseWorker;
import tr.com.thy.sensordatainterpreter.model.IDataBaseWorkerExtension;
import tr.com.thy.sensordatainterpreter.model.InterpreterData;
import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.model.Threshold;
import tr.com.thy.sensordatainterpreter.process.factory.EProcessType;
import tr.com.thy.sensordatainterpreter.process.factory.IProcess;

/**
 * The Class OperationProcess manages the Operational Process life cycle.
 */
@Service
public class OperationProcess implements IProcess {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(OperationProcess.class);

	/** The mongo database. */
	@Autowired
	@Qualifier("mongoWorker")
	private IDataBaseWorkerExtension mongoDatabase;

	/** The h 2 database. */
	@Autowired
	@Qualifier("h2Worker")
	private IDataBaseWorker h2Database;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@Override
	public EProcessType getType() {
		return EProcessType.OPERATIONAL;
	}

	/**
	 * Save data.
	 *
	 * @param data the data
	 */
	@Override
	public void saveData(InterpreterData data) {
		logger.debug("Save request for InterpreterData");
		if (data instanceof SensorData) {
			SensorData sensorData = (SensorData) data;
			Threshold thresholdDB = (Threshold) this.retrieveData(sensorData.getId(), Threshold.class);
			if (thresholdDB != null) {
				boolean inBoundry = sensorData.getBatteryVoltage() < thresholdDB.getThresholdValue() ? true : false;
				BatteryChange batteryChange = new BatteryChange(inBoundry, "Voltage", sensorData);
				mongoDatabase.save(batteryChange);
			}
		}
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
		Object retVal = null;
		if (clz.equals(Threshold.class)) {
			retVal = h2Database.findById(id, Threshold.class);
		} else {
			retVal = mongoDatabase.findById(id, clz);
		}
		return retVal;
	}

}
