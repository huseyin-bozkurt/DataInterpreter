package tr.com.thy.sensordatainterpreter.db.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.thy.sensordatainterpreter.model.Threshold;

/**
 * The Interface IThresholdRepository.
 */
@Repository
public interface IThresholdRepository extends JpaRepository<Threshold, String> {
}
