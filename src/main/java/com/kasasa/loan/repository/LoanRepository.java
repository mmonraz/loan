package com.kasasa.loan.repository;

import com.kasasa.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Loan findLoanById(Integer Id);
}
