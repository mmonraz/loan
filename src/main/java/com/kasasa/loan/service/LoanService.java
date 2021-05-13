package com.kasasa.loan.service;

import com.kasasa.loan.model.Loan;

public interface LoanService {
    Loan getLoan(Integer id);
    Loan saveLoan(Loan loan);
}
