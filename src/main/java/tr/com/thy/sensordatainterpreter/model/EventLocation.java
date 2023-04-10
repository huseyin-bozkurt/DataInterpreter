package tr.com.thy.sensordatainterpreter.model;

/**
 * The Class EventLocation.
 */
public class EventLocation {

	/** The geometry. */
	private IGeometry geometry;
	
	/**
	 * Instantiates a new event location.
	 */
	public EventLocation() {
		
	}

	/**
	 * Instantiates a new event location.
	 *
	 * @param geometry the geometry
	 */
	public EventLocation(IGeometry geometry) {
		super();
		this.geometry = geometry;
	}

	/**
	 * Gets the geometry.
	 *
	 * @return the geometry
	 */
	public IGeometry getGeometry() {
		return geometry;
	}

	/**
	 * Sets the geometry.
	 *
	 * @param geometry the new geometry
	 */
	public void setGeometry(IGeometry geometry) {
		this.geometry = geometry;
	}

}
