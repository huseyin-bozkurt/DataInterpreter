package tr.com.thy.sensordatainterpreter.model;

import java.util.List;

/**
 * The Interface IDataBaseWorkerExtension.
 */
public interface IDataBaseWorkerExtension extends IDataBaseWorker {
	
	/**
	 * Find by device idand event time.
	 *
	 * @param deviceId the device id
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return the list
	 */
	List<SensorData> findByDeviceIdandEventTime(String deviceId, long startTime, long endTime);
}
