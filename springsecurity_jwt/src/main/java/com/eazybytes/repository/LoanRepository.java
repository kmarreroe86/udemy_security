package com.eazybytes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.eazybytes.model.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	
	@PreAuthorize("hasRole('USER')")	// check the role before computation. If the rol is not assigned, 403 is returned
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);


}
