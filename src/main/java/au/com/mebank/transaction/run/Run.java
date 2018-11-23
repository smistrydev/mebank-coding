package au.com.mebank.transaction.run;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import au.com.mebank.transaction.application.TransactionBalanceApplication;
import au.com.mebank.transaction.domain.TransactionRecord;

public class Run {

	public static void main(String[] args) {

		if (args.length < 1) {
			throw new RuntimeException("Please supply parameter e.g: \"src/main/resources/sample_input.csv,ACC334455,20/10/2018 12:00:00,20/10/2018 19:00:00\"");
		}

		List<String> params = Arrays.asList(args[0].split(","));

		String inputFilePath = params.get(0);
		String accountId = params.get(1);
		LocalDateTime dateFrom = LocalDateTime.parse(params.get(2), TransactionRecord.CREATED_AT_DATE_FORMATTER);
		LocalDateTime dateTo = LocalDateTime.parse(params.get(3), TransactionRecord.CREATED_AT_DATE_FORMATTER);

		TransactionBalanceApplication application = new TransactionBalanceApplication();
		application.initalize(inputFilePath);
		application.printBalance(accountId, dateFrom, dateTo);

	}

}
