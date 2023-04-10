package tr.com.thy.sensordatainterpreter.db.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.thy.sensordatainterpreter.model.SensorData;

/**
 * The Interface ISensorDataMongoRepository.
 */
@Repository
public interface ISensorDataMongoRepository extends CrudRepository<SensorData, String> {


	/**
	 * Find SensorData by device id and time.
	 *
	 * @param deviceId the device id
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return the list
	 */
	@Query("{'$and':[ {'status_changes.device_id': ?0}, {'status_changes.event_timen':{$gt:?1,$lt:?2}} ] }")
	List<SensorData> findByDeviceIdAndTime(String deviceId, long startTime, long endTime);
}
