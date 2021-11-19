package com.practice.littlebanker.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.littlebanker.models.Account;
import com.practice.littlebanker.service.BankService;

@RestController
public class SimpleAccountManagement {

	@Autowired
	private BankService bankService;

	@GetMapping("/viewSummaryByAccount/{iban}")
	public Optional<Account> viewSummaryByAccount(@PathVariable int iban) {

		return bankService.viewSummaryByAccount(iban);

	}

}
