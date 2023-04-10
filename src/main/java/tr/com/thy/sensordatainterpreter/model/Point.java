package tr.com.thy.sensordatainterpreter.model;

import java.util.List;

/**
 * The Class Point.
 */
public class Point implements IGeometry {

	/** The coordinates. */
	private List<Double> coordinates;

	/**
	 * Instantiates a new point.
	 */
	public Point() {

	}

	/**
	 * Instantiates a new point.
	 *
	 * @param coordinates the coordinates
	 */
	public Point(List<Double> coordinates) {
		super();
		this.coordinates = coordinates;
	}

	/**
	 * Gets the coordinates.
	 *
	 * @return the coordinates
	 */
	public List<Double> getCoordinates() {
		return coordinates;
	}

	/**
	 * Sets the coordinates.
	 *
	 * @param coordinates the new coordinates
	 */
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

}
