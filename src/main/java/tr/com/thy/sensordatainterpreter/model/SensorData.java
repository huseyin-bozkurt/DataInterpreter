package tr.com.thy.sensordatainterpreter.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * The Class SensorData.
 */
@Document("sensorData")
public class SensorData implements Serializable, InterpreterData {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private String id;
	
	/** The type. */
	private String type;
	
	/** The temperature. */
	private double temperature;
	
	/** The air pressure. */
	private double airPressure;
	
	/** The humidity. */
	private double humidity;
	
	/** The light level. */
	private double lightLevel;
	
	/** The battery charge. */
	private double batteryCharge;
	
	/** The battery voltage. */
	private double batteryVoltage;
	
	/** The status changes. */
	private List<StatusChange> status_changes;

	/**
	 * Instantiates a new sensor data.
	 */
	public SensorData() {

	}

	/**
	 * Instantiates a new sensor data.
	 *
	 * @param id the id
	 * @param type the type
	 * @param temperature the temperature
	 * @param airPressure the air pressure
	 * @param humidity the humidity
	 * @param lightLevel the light level
	 * @param batteryCharge the battery charge
	 * @param batteryVoltage the battery voltage
	 * @param status_changes the status changes
	 */
	public SensorData(String id, String type, double temperature, double airPressure, double humidity,
			double lightLevel, double batteryCharge, double batteryVoltage, List<StatusChange> status_changes) {
		super();
		this.id = id;
		this.type = type;
		this.temperature = temperature;
		this.airPressure = airPressure;
		this.humidity = humidity;
		this.lightLevel = lightLevel;
		this.batteryCharge = batteryCharge;
		this.batteryVoltage = batteryVoltage;
		this.status_changes = status_changes;
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
	 * Gets the temperature.
	 *
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Sets the temperature.
	 *
	 * @param temperature the new temperature
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * Gets the air pressure.
	 *
	 * @return the air pressure
	 */
	public double getAirPressure() {
		return airPressure;
	}

	/**
	 * Sets the air pressure.
	 *
	 * @param airPressure the new air pressure
	 */
	public void setAirPressure(double airPressure) {
		this.airPressure = airPressure;
	}

	/**
	 * Gets the humidity.
	 *
	 * @return the humidity
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Sets the humidity.
	 *
	 * @param humidity the new humidity
	 */
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	/**
	 * Gets the light level.
	 *
	 * @return the light level
	 */
	public double getLightLevel() {
		return lightLevel;
	}

	/**
	 * Sets the light level.
	 *
	 * @param lightLevel the new light level
	 */
	public void setLightLevel(double lightLevel) {
		this.lightLevel = lightLevel;
	}

	/**
	 * Gets the battery charge.
	 *
	 * @return the battery charge
	 */
	public double getBatteryCharge() {
		return batteryCharge;
	}

	/**
	 * Sets the battery charge.
	 *
	 * @param batteryCharge the new battery charge
	 */
	public void setBatteryCharge(double batteryCharge) {
		this.batteryCharge = batteryCharge;
	}

	/**
	 * Gets the battery voltage.
	 *
	 * @return the battery voltage
	 */
	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	/**
	 * Sets the battery voltage.
	 *
	 * @param batteryVoltage the new battery voltage
	 */
	public void setBatteryVoltage(double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	/**
	 * Gets the status changes.
	 *
	 * @return the status changes
	 */
	public List<StatusChange> getStatus_changes() {
		return status_changes;
	}

	/**
	 * Sets the status changes.
	 *
	 * @param status_changes the new status changes
	 */
	public void setStatus_changes(List<StatusChange> status_changes) {
		this.status_changes = status_changes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String retVal = "";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			retVal = ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
