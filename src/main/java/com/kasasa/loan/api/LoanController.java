package com.kasasa.loan.api;

import com.kasasa.loan.model.Loan;
import com.kasasa.loan.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    /**
     * @Author Miguel Monraz
     * @param id
     * @return The loan that corresponds to the given id
     *  It calculates the APR based on the other fields
     */
    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable("id") Integer id) {
        return loanService.getLoan(id);
    }

    /**
     * @Author Miguel Monraz
     * @param loan
     * @return the newly created Loan
     */
    @PostMapping
    public Loan saveLoan(@Valid @RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }


}
