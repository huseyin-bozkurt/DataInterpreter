package tr.com.thy.sensordatainterpreter.db.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import tr.com.thy.sensordatainterpreter.model.MongoDatabaseSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

/**
 * The Class SequenceGeneratorService is used to generate auto id for operated
 * data.
 */
@Service
public class SequenceGeneratorService {

	/** The mongo operations. */
	private MongoOperations mongoOperations;

	/**
	 * Instantiates a new sequence generator service.
	 *
	 * @param mongoOperations the mongo operations
	 */
	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	/**
	 * Generate sequence.
	 *
	 * @param seqName the seq name
	 * @return the string
	 */
	public String generateSequence(String seqName) {
		MongoDatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), MongoDatabaseSequence.class);
		return !Objects.isNull(counter) ? String.valueOf(counter.getSeq()) : "1";
	}
}
