package tr.com.thy.sensordatainterpreter.process.factory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A factory for creating Process objects.
 */
@Component
public class ProcessFactory {
	
	/** The process map. */
	private static Map<EProcessType, IProcess> processMap;

	/**
	 * Instantiates a new process factory.
	 *
	 * @param processList the process list
	 */
	@Autowired
	private ProcessFactory(List<IProcess> processList) {
		processMap = processList.stream().collect(Collectors.toUnmodifiableMap(IProcess::getType, Function.identity()));
	}

	/**
	 * Gets the process.
	 *
	 * @param processType the process type
	 * @return the process
	 */
	public static IProcess getProcess(EProcessType processType) {
		return Optional.ofNullable(processMap.get(processType)).orElseThrow(IllegalArgumentException::new);
	}

	/**
	 * Gets the process map.
	 *
	 * @return the process map
	 */
	public static Map<EProcessType, IProcess> getProcessMap() {
		return processMap;
	}
}
