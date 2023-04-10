package tr.com.thy.sensordatainterpreter.model;

/**
 * The Interface IDataBaseWorker.
 */
public interface IDataBaseWorker {

	/**
	 * Save.
	 *
	 * @param data the data
	 */
	void save(Object data);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @param clz the clz
	 * @return the object
	 */
	Object findById(String id, Class<?> clz);
}
