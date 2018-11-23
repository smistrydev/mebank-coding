package au.com.mebank.transaction.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import au.com.mebank.transaction.domain.AccountBalance;
import au.com.mebank.transaction.domain.TransactionRecord;

public class AccountBalanceProcessorTest {

	@Test
	public void shouldCreateAccountBalanceProcesor() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_basic.csv");
		String accountId = "ACC334455";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 12:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("20/10/2018 19:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		assertNotNull(balanceProcessor);
	}

	@Test
	public void shouldGetAccountBalanceACC334455() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_basic.csv");
		String accountId = "ACC334455";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 12:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("20/10/2018 19:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();
		assertNotNull(accountBalance);
		assertEquals(accountId, accountBalance.getAccountId());
		assertEquals(-25.0, accountBalance.getBalance(), 0.001);
		assertEquals(dateFrom, accountBalance.getDateFrom());
		assertEquals(dateTo, accountBalance.getDateTo());
		assertEquals(1, accountBalance.getNoOfTransactions());
	}

	@Test
	public void shouldGetAccountBalanceACC998877() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_basic.csv");
		String accountId = "ACC998877";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 12:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("20/10/2018 19:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();
		assertNotNull(accountBalance);
		assertEquals(accountId, accountBalance.getAccountId());
		assertEquals(-5.0, accountBalance.getBalance(), 0.001);
		assertEquals(dateFrom, accountBalance.getDateFrom());
		assertEquals(dateTo, accountBalance.getDateTo());
		assertEquals(1, accountBalance.getNoOfTransactions());
	}

	@Test
	public void shouldGetAccountBalanceACC778899() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_basic.csv");
		String accountId = "ACC778899";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 12:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("20/10/2018 19:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();
		assertNotNull(accountBalance);
		assertEquals(accountId, accountBalance.getAccountId());
		assertEquals(30.0, accountBalance.getBalance(), 0.001);
		assertEquals(dateFrom, accountBalance.getDateFrom());
		assertEquals(dateTo, accountBalance.getDateTo());
		assertEquals(2, accountBalance.getNoOfTransactions());
	}

	@Test
	public void shouldGetRecordsWithBalanceZero() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_cases.csv");
		String accountId = "ACC778890";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 01:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("22/10/2018 19:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();
		assertNotNull(accountBalance);
		assertEquals(accountId, accountBalance.getAccountId());
		assertEquals(0.0, accountBalance.getBalance(), 0.001);
		assertEquals(dateFrom, accountBalance.getDateFrom());
		assertEquals(dateTo, accountBalance.getDateTo());
		assertEquals(2, accountBalance.getNoOfTransactions());
	}

	@Test
	public void shouldGetRecordsWithReversalOutOfDateToWithDateEdgeCases() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_cases.csv");
		String accountId = "ACC778800";
		LocalDateTime dateFrom = LocalDateTime.parse("21/10/2018 12:47:55", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("21/10/2018 18:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();
		assertNotNull(accountBalance);
		assertEquals(accountId, accountBalance.getAccountId());
		assertEquals(-123.0, accountBalance.getBalance(), 0.001);
		assertEquals(dateFrom, accountBalance.getDateFrom());
		assertEquals(dateTo, accountBalance.getDateTo());
		assertEquals(1, accountBalance.getNoOfTransactions());
	}
	@Test
	public void shouldGetRecordsWithDateFromEdgeCases() {
		List<TransactionRecord> transactionRecords = new InputFileProcessor().process("src/test/resources/test_input_cases.csv");
		String accountId = "ACC334455";
		LocalDateTime dateFrom = LocalDateTime.parse("20/10/2018 09:20:32", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse("21/10/2018 02:00:00", TransactionRecord.CREATED_AT_DATE_FORMATTER);
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();
		assertNotNull(accountBalance);
		assertEquals(accountId, accountBalance.getAccountId());
		assertEquals(-54.25, accountBalance.getBalance(), 0.001);
		assertEquals(dateFrom, accountBalance.getDateFrom());
		assertEquals(dateTo, accountBalance.getDateTo());
		assertEquals(4, accountBalance.getNoOfTransactions());
	}

}
