package au.com.mebank.transaction.process;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import au.com.mebank.transaction.domain.TransactionRecord;
import au.com.mebank.transaction.domain.TransactionRecord.TransactionType;
import au.com.mebank.transaction.util.FileUtil;

public class InputFileProcessor {

	private static final String COMMA = ",";

	public InputFileProcessor() {
	}

	public List<TransactionRecord> process(String inputFilePath) {
		BufferedReader reader = FileUtil.getFileReader(inputFilePath);
		List<TransactionRecord> transactionRecords = reader.lines().skip(1).map(mapRowToRecord).collect(Collectors.toList());
		FileUtil.closeReader(reader);
		return transactionRecords;
	}

	private Function<String, TransactionRecord> mapRowToRecord = (row) -> {
		String[] field = row.split(COMMA);
		Arrays.parallelSetAll(field, (i) -> field[i].trim());
		TransactionRecord record = new TransactionRecord();
		record.setTransactionId(field[0]);
		record.setFromAccountId(field[1]);
		record.setToAccountId(field[2]);
		record.setCreatedAt(LocalDateTime.parse(field[3], TransactionRecord.CREATED_AT_DATE_FORMATTER));
		record.setAmount(Double.parseDouble(field[4]));
		record.setTransactionType(TransactionType.valueOf(field[5]));
		if (field.length == 7) {
			record.setRelatedTransaction(field[6]);
		}
		return record;
	};

}