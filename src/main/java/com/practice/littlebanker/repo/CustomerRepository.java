package com.practice.littlebanker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.littlebanker.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public void deleteByAccount_iban(int iban);

	@Modifying
	@Query(value = "Update customer u set u.nationality=?2 WHERE u.account_iban = ?1 ", nativeQuery = true)
	public void UpdateByNationality(int iban, String nationality);

}
