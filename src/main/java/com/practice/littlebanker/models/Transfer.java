package com.practice.littlebanker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transferId;

	private String date;
	private float amount;
	private String message;

	@ManyToOne
	@JoinColumn(name = "debtor_iban")
	private Account debtorAccount;

	@ManyToOne
	@JoinColumn(name = "creditor_iban")
	private Account creditorAccount;

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Account getDebtorAccount() {
		return debtorAccount;
	}

	public void setDebtorAccount(Account debtorAccount) {
		this.debtorAccount = debtorAccount;
	}

	public Account getCreditorAccount() {
		return creditorAccount;
	}

	public void setCreditorAccount(Account creditorAccount) {
		this.creditorAccount = creditorAccount;
	}

}
