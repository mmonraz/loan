package com.kasasa.loan.api;

import com.kasasa.loan.model.Loan;
import com.kasasa.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanController {
    private LoanService loanService;

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable("id") Integer id) {
        return loanService.getLoan(id);
    }

    @PostMapping
    public Loan saveLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }


}
