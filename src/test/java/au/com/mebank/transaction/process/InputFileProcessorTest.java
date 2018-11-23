package au.com.mebank.transaction.process;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import au.com.mebank.transaction.domain.TransactionRecord;

public class InputFileProcessorTest {

	InputFileProcessor processor = new InputFileProcessor();

	@Test
	public void testProcess() {
		List<TransactionRecord> transactionRecords = processor.process("src/main/resources/sample_input.csv");
		assertNotNull(transactionRecords);
		assertEquals(transactionRecords.size(), 5);
	}

}
