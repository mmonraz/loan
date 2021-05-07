package com.kasasa.loan.service;

import com.kasasa.loan.model.Loan;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoanService {
    Loan getLoan(Integer id);
    Loan saveLoan(@RequestBody Loan loan);
}
