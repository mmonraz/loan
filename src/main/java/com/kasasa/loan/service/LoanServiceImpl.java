package com.kasasa.loan.service;

import com.kasasa.loan.model.Loan;
import com.kasasa.loan.model.LoanType;
import com.kasasa.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public Loan getLoan(Integer id) {

        Loan loan = loanRepository.findLoanById(id);

        loan.setApr(calculateAPR(loan));
        return loan;
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Double calculateAPR(Loan loan){
        Double apr = 0.00;

        double totalInterest = (loan.getLoan() * loan.getRate() * loan.getTerm())/100;

        double fees = 0.00;

        switch (loan.getLoanType()){
            case STUDENT:
                fees = LoanType.STUDENT.getLoanType();
                break;
            case AUTO:
                fees = LoanType.AUTO.getLoanType();
                break;
            case PERSONAL:
                fees = LoanType.PERSONAL.getLoanType();
                break;
            case MORTGAGE:
                fees = LoanType.MORTGAGE.getLoanType();
                break;
        }

        double feesAndInterestByLoan = (fees + totalInterest) / loan.getLoan();

        apr = ((feesAndInterestByLoan / loan.getTerm()) * 365) * 100;

        return apr;
    }
}
