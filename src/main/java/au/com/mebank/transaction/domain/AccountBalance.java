package au.com.mebank.transaction.domain;

import java.time.LocalDateTime;

public class AccountBalance {

	private String accountId;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	private double balance;
	private long noOfTransactions;

	public AccountBalance() {
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getNoOfTransactions() {
		return noOfTransactions;
	}

	public void setNoOfTransactions(long noOfTransactions) {
		this.noOfTransactions = noOfTransactions;
	}

	@Override
	public String toString() {
		return "AccountBalance [accountId=" + accountId + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", balance="
				+ balance + ", noOfTransactions=" + noOfTransactions + "]";
	}

}
