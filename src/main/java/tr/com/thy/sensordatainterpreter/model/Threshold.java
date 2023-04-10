package tr.com.thy.sensordatainterpreter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Threshold.
 */
@Entity
@Table(name = "threshold")
public class Threshold implements OperationData {

	/** The id. */
	@Id
	private String id;

	/** The threshold value. */
	@Column(nullable = false, name = "thresholdValue")
	private Integer thresholdValue;

	/** The type. */
	@Column(nullable = false, name = "type")
	private String type;

	/**
	 * Instantiates a new threshold.
	 */
	public Threshold() {
		super();
	}

	/**
	 * Instantiates a new threshold.
	 *
	 * @param id the id
	 * @param thresholdValue the threshold value
	 * @param type the type
	 */
	public Threshold(String id, Integer thresholdValue, String type) {
		super();
		this.id = id;
		this.thresholdValue = thresholdValue;
		this.type = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * Gets the threshold value.
	 *
	 * @return the threshold value
	 */
	public Integer getThresholdValue() {
		return thresholdValue;
	}

	/**
	 * Sets the threshold value.
	 *
	 * @param thresholdValue the new threshold value
	 */
	public void setThresholdValue(Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

}
