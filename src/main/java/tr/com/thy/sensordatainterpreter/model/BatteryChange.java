package tr.com.thy.sensordatainterpreter.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class BatteryChange.
 */
@Document("batteryChange")
public class BatteryChange implements Serializable, InterpreterData {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant SEQUENCE_NAME. */
	@Transient
	public static final String SEQUENCE_NAME = "batterychange_sequence";

	/** The id. */
	@Id
	private String id;

	/** The battery changed needed. */
	private boolean batteryChangedNeeded;

	/** The type. */
	private String type;

	/** The sensor data. */
	private SensorData sensorData;

	/**
	 * Instantiates a new battery change.
	 */
	public BatteryChange() {
	}

	/**
	 * Instantiates a new battery change.
	 *
	 * @param batteryChangedNeeded the battery changed needed
	 * @param type                 the type
	 * @param sensorData           the sensor data
	 */
	public BatteryChange(boolean batteryChangedNeeded, String type, SensorData sensorData) {
		super();
		this.batteryChangedNeeded = batteryChangedNeeded;
		this.type = type;
		this.sensorData = sensorData;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
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
	 * Checks if is battery changed needed.
	 *
	 * @return true, if is battery changed needed
	 */
	public boolean isBatteryChangedNeeded() {
		return batteryChangedNeeded;
	}

	/**
	 * Sets the battery changed needed.
	 *
	 * @param batteryChangedNeeded the new battery changed needed
	 */
	public void setBatteryChangedNeeded(boolean batteryChangedNeeded) {
		this.batteryChangedNeeded = batteryChangedNeeded;
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
	 * Gets the sensor data.
	 *
	 * @return the sensor data
	 */
	public SensorData getSensorData() {
		return sensorData;
	}

	/**
	 * Sets the sensor data.
	 *
	 * @param sensorData the new sensor data
	 */
	public void setSensorData(SensorData sensorData) {
		this.sensorData = sensorData;
	}

}
