package tr.com.thy.sensordatainterpreter.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tr.com.thy.sensordatainterpreter.model.SensorData;
import tr.com.thy.sensordatainterpreter.model.StatusChange;
import tr.com.thy.sensordatainterpreter.process.factory.IProcessExtension;

/**
 * The Class StatusChangeController is the rest controller to provide web
 * functionality.
 */
@RestController
public class StatusChangeController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(StatusChangeController.class);

	/** The statistic process. */
	@Autowired
	private IProcessExtension statisticProcess;

	/**
	 * Gets the status changes.
	 *
	 * @param deviceId  the device id
	 * @param startTime the start time
	 * @param endTime   the end time
	 * @return the status changes
	 */
	@GetMapping("/statusChanges")
	public ResponseEntity<List<List<StatusChange>>> getStatusChanges(@RequestParam(required = true) String deviceId,
			@RequestParam(required = true) long startTime, @RequestParam(required = true) long endTime) {
		logger.debug("Request received on StatusChangeController with parameters {} {} {}", deviceId, startTime,
				endTime);
		try {
			List<SensorData> sensorDataList = statisticProcess.findByDeviceId(deviceId, startTime, endTime);
			if (sensorDataList != null) {
				List<List<StatusChange>> statusChangeList = sensorDataList.stream().map(SensorData::getStatus_changes)
						.collect(Collectors.toList());
				logger.debug("Response on StatusChangeController:");
				statusChangeList.forEach(sc -> sc.forEach(s -> logger.debug(s.toString())));
				return new ResponseEntity<>(statusChangeList, HttpStatus.OK);
			} else {
				logger.warn("Response NOT_FOUND on StatusChangeController");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Response INTERNAL_SERVER_ERROR on StatusChangeController {}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
