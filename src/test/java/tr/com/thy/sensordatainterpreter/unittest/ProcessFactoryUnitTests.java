package tr.com.thy.sensordatainterpreter.unittest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tr.com.thy.sensordatainterpreter.process.factory.EProcessType;
import tr.com.thy.sensordatainterpreter.process.factory.IProcess;
import tr.com.thy.sensordatainterpreter.process.factory.ProcessFactory;
import tr.com.thy.sensordatainterpreter.processes.OperationProcess;
import tr.com.thy.sensordatainterpreter.processes.StatisticProcess;

@SpringBootTest
public class ProcessFactoryUnitTests {

	@Test
	void processFactoryTest() throws InterruptedException {
		IProcess statisticalProcess = ProcessFactory.getProcess(EProcessType.STATISTICAL);
		assertTrue(statisticalProcess instanceof StatisticProcess);

		IProcess operationalProcess = ProcessFactory.getProcess(EProcessType.OPERATIONAL);
		assertTrue(operationalProcess instanceof OperationProcess);

	}

}
