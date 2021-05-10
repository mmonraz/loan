package com.kasasa.loan.service;

import com.kasasa.loan.exception.LoanNotFoundException;
import com.kasasa.loan.model.Loan;
import com.kasasa.loan.model.LoanType;
import com.kasasa.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final static DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

    @Override
    public Loan getLoan(Integer id) {

        Loan loan = loanRepository.findLoanById(id).orElseThrow(() -> new LoanNotFoundException("Loan Not found"));

        if(loan != null){
            loan.setApr(calculateAPR(loan));

        }

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
