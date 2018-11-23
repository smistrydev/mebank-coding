package au.com.mebank.transaction.application;

import java.time.LocalDateTime;
import java.util.List;

import au.com.mebank.transaction.domain.AccountBalance;
import au.com.mebank.transaction.domain.TransactionRecord;
import au.com.mebank.transaction.process.AccountBalanceProcessor;
import au.com.mebank.transaction.process.InputFileProcessor;

public class TransactionBalanceApplication {

	private List<TransactionRecord> transactionRecords;

	public TransactionBalanceApplication() {
	}

	public void initalize(String inputFilePath) {
		InputFileProcessor fileProcessor = new InputFileProcessor();
		this.transactionRecords = fileProcessor.process(inputFilePath);
		//System.out.println("Initialization complete");
		System.out.println("Initialization complete");
	}

	public void printBalance(String accountId, LocalDateTime dateFrom, LocalDateTime dateTo) {
		AccountBalanceProcessor balanceProcessor = new AccountBalanceProcessor(this.transactionRecords, accountId, dateFrom, dateTo);
		AccountBalance accountBalance = balanceProcessor.process();

		System.out.println("Relative balance for the period is: " + TransactionRecord.CURRENCY_FORMATTER.format(accountBalance.getBalance()));
		System.out.println("Number of transactions included is: " + TransactionRecord.COUNT_FORMATTER.format(accountBalance.getNoOfTransactions()));
	}

}
