package tr.com.thy.sensordatainterpreter.process.factory;

import java.util.List;

import tr.com.thy.sensordatainterpreter.model.SensorData;

/**
 * The Interface IProcessExtension extends process definition to provide new
 * functionality.
 */
public interface IProcessExtension extends IProcess {

	/**
	 * Find by device id.
	 *
	 * @param deviceId  the device id
	 * @param startTime the start time
	 * @param endTime   the end time
	 * @return the list
	 */
	List<SensorData> findByDeviceId(String deviceId, long startTime, long endTime);
}
