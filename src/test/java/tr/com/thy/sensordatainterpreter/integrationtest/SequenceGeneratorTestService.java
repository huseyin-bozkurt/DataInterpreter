package tr.com.thy.sensordatainterpreter.integrationtest;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import tr.com.thy.sensordatainterpreter.model.MongoDatabaseSequence;

@Service
@Profile("unittest")
public class SequenceGeneratorTestService {

	private MongoOperations mongoOperations;

	@Autowired
	public SequenceGeneratorTestService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public long deleteSequence(String seqName) {
		DeleteResult remove = mongoOperations.remove(query(where("_id").is(seqName)), MongoDatabaseSequence.class);
		return remove.getDeletedCount();
	}
}
