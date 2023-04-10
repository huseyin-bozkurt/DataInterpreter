package tr.com.thy.sensordatainterpreter.db.h2;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import tr.com.thy.sensordatainterpreter.model.IDataBaseWorker;
import tr.com.thy.sensordatainterpreter.model.OperationData;
import tr.com.thy.sensordatainterpreter.model.Threshold;

/**
 * The Class H2Worker is used to do required queries on H2 DB.
 */
@Component
@Qualifier("h2Worker")
public class H2Worker implements IDataBaseWorker {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(H2Worker.class);

	/** The threshold repository. */
	@Autowired
	private IThresholdRepository thresholdRepository;

	/**
	 * Save.
	 *
	 * @param data the data
	 */
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void save(Object data) {
		try {
			thresholdRepository.save((Threshold) data);
		} catch (Exception e) {
			logger.error("Error occured during Save operation: {}", e);
		}
	}

	/**
	 * Find by id.
	 *
	 * @param id  the id
	 * @param clz the clz
	 * @return the operation data
	 */
	@Override
	@Transactional(readOnly = true)
	public OperationData findById(String id, Class<?> clz) {
		OperationData retVal = null;
		try {
			Optional<Threshold> optionalThreshold = thresholdRepository.findById(id);
			if (optionalThreshold.isPresent()) {
				retVal = (Threshold) optionalThreshold.get();
			}
		} catch (Exception e) {
			logger.error("Error occured during findById operation: {}", e);
		}
		return retVal;
	}

}
