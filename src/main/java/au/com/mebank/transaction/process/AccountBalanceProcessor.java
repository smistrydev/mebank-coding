package au.com.mebank.transaction.process;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import au.com.mebank.transaction.domain.AccountBalance;
import au.com.mebank.transaction.domain.TransactionRecord;
import au.com.mebank.transaction.domain.TransactionRecord.TransactionType;

public class AccountBalanceProcessor {

	private List<TransactionRecord> transactionRecords;
	private AccountBalance accountBalance = new AccountBalance();

	public AccountBalanceProcessor(final List<TransactionRecord> transactionRecords, String accountId, LocalDateTime dateFrom, LocalDateTime dateTo) {
		accountBalance.setAccountId(accountId);
		accountBalance.setDateFrom(dateFrom);
		accountBalance.setDateTo(dateTo);
		this.transactionRecords = transactionRecords;
	}

	public AccountBalance process() {
		List<TransactionRecord> filteredTransactionRecords = transactionRecords
				.stream()
				.filter(x -> filterInvolvedRecord(accountBalance, x))
				.collect(Collectors.toList());
		filteredTransactionRecords = this.removeReversedTransaction(filteredTransactionRecords);
		accountBalance.setBalance(filteredTransactionRecords
				.stream()
				.mapToDouble(TransactionRecord::getAmount)
				.sum());
		accountBalance.setNoOfTransactions(filteredTransactionRecords.size());
		return accountBalance;
	}

	private boolean filterInvolvedRecord(AccountBalance accountBalance, TransactionRecord record) {
		if (this.accountToOrFrom(accountBalance.getAccountId(), record) 
				&& (this.isReversal(record) 
						|| this.isWithInDates(record, accountBalance.getDateFrom(), accountBalance.getDateTo()))) {
			return true;
		}
		return false;
	}

	private List<TransactionRecord> removeReversedTransaction(List<TransactionRecord> filteredTransactionRecords) {
		// Get all reversal transaction Id
		List<String> reverseTransactionIds = filteredTransactionRecords
				.stream()
				.filter(x -> x.getTransactionType() == TransactionType.REVERSAL)
				.map(x -> x.getRelatedTransaction())
				.collect(Collectors.toList());
		// Get all payment record that are not reversed
		List<TransactionRecord> filteredRecords = filteredTransactionRecords
				.stream()
				.filter(x -> ((x.getTransactionType() == TransactionType.PAYMENT) && !reverseTransactionIds.contains(x.getTransactionId())))
				.collect(Collectors.toList());
		return filteredRecords;
	}
	
	private boolean accountToOrFrom(String accountId, TransactionRecord record) {
		// Convert about to negative if it is withdrawal. 
		if (record.getFromAccountId().equals(accountId)) {
			record.setAmount(record.getAmount() * -1);
			return true;
		} else if (record.getToAccountId().equals(accountId)) {
			return true;
		}
		return false;
	}

	private boolean isReversal(TransactionRecord record) {
		return record.getTransactionType() == TransactionType.REVERSAL;
	}

	private boolean isWithInDates(TransactionRecord record, LocalDateTime dateFrom, LocalDateTime dateTo) {
		if (!record.getCreatedAt().isBefore(dateFrom) && !record.getCreatedAt().isAfter(dateTo)) {
			return true;
		}
		return false;
	}

}
