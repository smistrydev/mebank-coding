/**
 * 
 */
package au.com.mebank.transaction.domain;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sanjaymistry
 *
 */
public class TransactionRecord {

	public static final DateTimeFormatter CREATED_AT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public static final DecimalFormat CURRENCY_FORMATTER = new DecimalFormat("$###,###,##0.00");
	public static final DecimalFormat COUNT_FORMATTER = new DecimalFormat("###,###,##0");

	private String transactionId;
	private String fromAccountId;
	private String toAccountId;
	private LocalDateTime createdAt;
	private double amount;
	private TransactionType transactionType;
	private String relatedTransaction;

	/*
	 * 
	 */
	public TransactionRecord() {
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}

	@Override
	public String toString() {
		return "TransactionRecord [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", createdAt=" + createdAt + ", amount=" + amount + ", transactionType=" + transactionType + ", relatedTransaction=" + relatedTransaction + "]";
	}

	public enum TransactionType {
		PAYMENT, REVERSAL
	}
}
