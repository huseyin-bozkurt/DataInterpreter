package tr.com.thy.sensordatainterpreter.process.factory;

import tr.com.thy.sensordatainterpreter.model.InterpreterData;

/**
 * The Interface IProcess defines process.
 */
public interface IProcess {

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	EProcessType getType();

	/**
	 * Save data.
	 *
	 * @param data the data
	 */
	void saveData(InterpreterData data);

	/**
	 * Retrieve data.
	 *
	 * @param id  the id
	 * @param clz the clz
	 * @return the object
	 */
	Object retrieveData(String id, Class<?> clz);
}
