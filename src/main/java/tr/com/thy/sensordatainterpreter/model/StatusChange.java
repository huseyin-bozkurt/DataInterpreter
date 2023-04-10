package tr.com.thy.sensordatainterpreter.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * The Class StatusChange.
 */
public class StatusChange {
	
	/** The device id. */
	private String device_id;
	
	/** The vehicle id. */
	private String vehicle_id;
	
	/** The vehicle type. */
	private String vehicle_type;
	
	/** The propulsion type. */
	private List<String> propulsion_type;
	
	/** The event type. */
	private String event_type;
	
	/** The event type reason. */
	private String event_type_reason;
	
	/** The event timen. */
	private long event_timen;
	
	/** The event location. */
	private EventLocation event_location;

	/**
	 * Instantiates a new status change.
	 */
	public StatusChange() {

	}

	/**
	 * Instantiates a new status change.
	 *
	 * @param device_id the device id
	 * @param vehicle_id the vehicle id
	 * @param vehicle_type the vehicle type
	 * @param propulsion_type the propulsion type
	 * @param event_type the event type
	 * @param event_type_reason the event type reason
	 * @param event_timen the event timen
	 * @param event_location the event location
	 */
	public StatusChange(String device_id, String vehicle_id, String vehicle_type, List<String> propulsion_type,
			String event_type, String event_type_reason, long event_timen, EventLocation event_location) {
		super();
		this.device_id = device_id;
		this.vehicle_id = vehicle_id;
		this.vehicle_type = vehicle_type;
		this.propulsion_type = propulsion_type;
		this.event_type = event_type;
		this.event_type_reason = event_type_reason;
		this.event_timen = event_timen;
		this.event_location = event_location;
	}

	/**
	 * Gets the device id.
	 *
	 * @return the device id
	 */
	public String getDevice_id() {
		return device_id;
	}

	/**
	 * Sets the device id.
	 *
	 * @param device_id the new device id
	 */
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	/**
	 * Gets the vehicle id.
	 *
	 * @return the vehicle id
	 */
	public String getVehicle_id() {
		return vehicle_id;
	}

	/**
	 * Sets the vehicle id.
	 *
	 * @param vehicle_id the new vehicle id
	 */
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	/**
	 * Gets the vehicle type.
	 *
	 * @return the vehicle type
	 */
	public String getVehicle_type() {
		return vehicle_type;
	}

	/**
	 * Sets the vehicle type.
	 *
	 * @param vehicle_type the new vehicle type
	 */
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	/**
	 * Gets the propulsion type.
	 *
	 * @return the propulsion type
	 */
	public List<String> getPropulsion_type() {
		return propulsion_type;
	}

	/**
	 * Sets the propulsion type.
	 *
	 * @param propulsion_type the new propulsion type
	 */
	public void setPropulsion_type(List<String> propulsion_type) {
		this.propulsion_type = propulsion_type;
	}

	/**
	 * Gets the event type.
	 *
	 * @return the event type
	 */
	public String getEvent_type() {
		return event_type;
	}

	/**
	 * Sets the event type.
	 *
	 * @param event_type the new event type
	 */
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	/**
	 * Gets the event type reason.
	 *
	 * @return the event type reason
	 */
	public String getEvent_type_reason() {
		return event_type_reason;
	}

	/**
	 * Sets the event type reason.
	 *
	 * @param event_type_reason the new event type reason
	 */
	public void setEvent_type_reason(String event_type_reason) {
		this.event_type_reason = event_type_reason;
	}

	/**
	 * Gets the event timen.
	 *
	 * @return the event timen
	 */
	public long getEvent_timen() {
		return event_timen;
	}

	/**
	 * Sets the event timen.
	 *
	 * @param event_timen the new event timen
	 */
	public void setEvent_timen(long event_timen) {
		this.event_timen = event_timen;
	}

	/**
	 * Gets the event location.
	 *
	 * @return the event location
	 */
	public EventLocation getEvent_location() {
		return event_location;
	}

	/**
	 * Sets the event location.
	 *
	 * @param event_location the new event location
	 */
	public void setEvent_location(EventLocation event_location) {
		this.event_location = event_location;
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
