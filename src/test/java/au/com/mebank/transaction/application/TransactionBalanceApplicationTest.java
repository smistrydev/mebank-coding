package au.com.mebank.transaction.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.Test;

import au.com.mebank.transaction.domain.TransactionRecord;

public class TransactionBalanceApplicationTest {

	TransactionBalanceApplication application = new TransactionBalanceApplication();

	@Test
	public void shouldGetInstanceOfTransactionBalanceApplication() {
		assertNotNull(application);
	}

	@Test(expected = RuntimeException.class)
	public void shouldInitializeFail() {
		application.initalize("src/test/resources/test_input_wrong.csv");
	}

	@Test
	public void shouldInitializeSuccefully() {
		PrintStream oldStream = System.out;
		OutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		application.initalize("src/test/resources/test_input_basic.csv");
		String actualOut = outputStream.toString();
		System.setOut(oldStream);
		assertEquals("Initialization complete\n", actualOut);
	}

	@Test
	public void shouldPrintBalanceAsExpected() {
		PrintStream oldStream = System.out;
		OutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		application.initalize("src/test/resources/test_input_basic.csv");
		String accountId = "ACC334455";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 12:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("20/10/2018 19:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		application.printBalance(accountId, dateFrom, dateTo);
		String actualOut = outputStream.toString();
		System.setOut(oldStream);
		assertEquals("Initialization complete\n" + "Relative balance for the period is: -$25.00\n" + "Number of transactions included is: 1\n", actualOut);
	}

}