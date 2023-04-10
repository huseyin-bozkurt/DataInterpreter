package tr.com.thy.sensordatainterpreter.db.mongo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tr.com.thy.sensordatainterpreter.model.BatteryChange;

/**
 * The Interface IBatteryChangeMongoRepository.
 */
@Repository
public interface IBatteryChangeMongoRepository extends CrudRepository<BatteryChange, String> {
}
