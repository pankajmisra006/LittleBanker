package com.practice.littlebanker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.stereotype.Service;

import com.practice.littlebanker.exception.BankTransactionException;
import com.practice.littlebanker.models.Account;
import com.practice.littlebanker.models.Customer;
import com.practice.littlebanker.models.Transfer;
import com.practice.littlebanker.repo.AccountRepository;
import com.practice.littlebanker.repo.CustomerRepository;
import com.practice.littlebanker.repo.TransferRepository;

@Service
public class BankService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransferRepository transferRepository;

	public String createCustomer(Customer customer) {
		customerRepository.save(customer);
		return "Customer and Account Created";

	}

	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();

	}

	@Transactional
	public String removeCustomer(int iban) {
		customerRepository.deleteByAccount_iban(iban);
		return "Sucessfully removed";

	}

	@Transactional
	public String modifyNationality(int iban, String nationality) {
		customerRepository.UpdateByNationality(iban, nationality);
		return "Sucessfully removed";

	}

	public Optional<Account> viewSummaryByAccount(int iban) {
		Optional<Account> acc = accountRepository.findById(iban);
		return acc;
	}

	public String transferAmount(Transfer transfer) throws BankTransactionException {
		sendMoney(transfer.getDebtorAccount().getIban(), transfer.getCreditorAccount().getIban(), transfer.getAmount(),
				transfer);
		return "Transaction SucessFull";

	}

	public Optional<Transfer> findTransaction(Integer transferId) {
		return transferRepository.findById(transferId);

	}

	public List<Transfer> findTransactionByAmount(float amount) {
		return transferRepository.findByAmount(amount);

	}

	public List<Transfer> findTransactionByIban(int iban) {
		return transferRepository.findByDebtor_ibanOrcreditor_iban(iban);

	}

	public List<Transfer> findTransactionByMessage(String message) {
		return transferRepository.findByMessage(message);

	}

	@Transactional
	public Account addAmount(int id, float amount) throws BankTransactionException {
		Optional<Account> account = accountRepository.findById(id);
		if (account == null) {
			throw new BankTransactionException("Account not found " + id);
		}
		float newBalance = account.get().getBalance() + amount;
		if (account.get().getBalance() + amount < 0) {
			throw new BankTransactionException(
					"The money in the account '" + id + "' is not enough (" + account.get().getBalance() + ")");
		}
		account.get().setBalance(newBalance);
		return account.get();

	}

	@Transactional(rollbackOn = BankTransactionException.class)
	public void sendMoney(int fromAccountId, int toAccountId, float amount, Transfer transfer)
			throws BankTransactionException {
		Account creditedAcount = addAmount(toAccountId, amount);
		Account debitedAcount = addAmount(fromAccountId, -amount);
		transferRepository.save(transfer);
		accountRepository.save(debitedAcount);
		accountRepository.save(creditedAcount);

	}

}
