package tr.com.thy.sensordatainterpreter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class MongoDatabaseSequence.
 */
@Document(collection = "database_sequences")
public class MongoDatabaseSequence {

	/** The id. */
	@Id
	private String id;

	/** The seq. */
	private long seq;

	/**
	 * Instantiates a new mongo database sequence.
	 */
	public MongoDatabaseSequence() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public long getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(long seq) {
		this.seq = seq;
	}
}
