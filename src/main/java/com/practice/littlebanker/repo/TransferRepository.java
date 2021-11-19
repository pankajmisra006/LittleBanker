package com.practice.littlebanker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.littlebanker.models.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	public List<Transfer> findByAmount(float amount);

	@Query(value = "SELECT * FROM transfer u WHERE u.debtor_iban = ?1 or u.creditor_iban = ?1 ", nativeQuery = true)
	public List<Transfer> findByDebtor_ibanOrcreditor_iban(int iban);

	public List<Transfer> findByMessage(String message);

}
