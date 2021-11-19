package com.practice.littlebanker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.littlebanker.exception.BankTransactionException;
import com.practice.littlebanker.models.Transfer;
import com.practice.littlebanker.service.BankService;

@RestController
public class Transactions {

	@Autowired
	private BankService bankService;

	@PostMapping("/transfer")
	public String transferAmount(@RequestBody Transfer transfer) throws BankTransactionException {

		return bankService.transferAmount(transfer);

	}

	@GetMapping("/transfer/{transferId}")
	public Optional<Transfer> transferAmount(@PathVariable Integer transferId) {
		return bankService.findTransaction(transferId);

	}

	@GetMapping("/findTransactionByAmount/{amount}")
	public List<Transfer> findTransactionByAmount(@PathVariable Float amount) {
		return bankService.findTransactionByAmount(amount);

	}

	@GetMapping("/findTransactionByIban/{iban}")
	public List<Transfer> findTransactionByIban(@PathVariable int iban) {
		return bankService.findTransactionByIban(iban);

	}

	@GetMapping("/findTransactionByMessage/{message}")
	public List<Transfer> findTransactionByMessage(@PathVariable String message) {
		return bankService.findTransactionByMessage(message);

	}

}
