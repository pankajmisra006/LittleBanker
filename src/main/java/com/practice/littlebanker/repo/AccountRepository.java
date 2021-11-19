package com.practice.littlebanker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.littlebanker.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public void deleteByIban(int iban);

}
