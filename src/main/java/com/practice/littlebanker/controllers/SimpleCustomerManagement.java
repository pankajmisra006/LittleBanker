package com.practice.littlebanker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.littlebanker.models.Customer;
import com.practice.littlebanker.repo.CustomerRepository;
import com.practice.littlebanker.service.BankService;

@RestController
public class SimpleCustomerManagement {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankService bankService;

	@PostMapping("/createCustomer")
	public String createCustomer(@RequestBody Customer customer) {

		String result = bankService.createCustomer(customer);
		return result;
	}

	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer() {

		return bankService.getAllCustomer();

	}

	@DeleteMapping("/removeCustomerByAccountNo/{iban}")
	public String removeCustomer(@PathVariable int iban) {

		return bankService.removeCustomer(iban);

	}

	@DeleteMapping("/modifyNationality/{iban}/{nationality}")
	public String modifyNationality(@PathVariable int iban, @PathVariable String nationality) {

		return bankService.modifyNationality(iban, nationality);

	}

}
