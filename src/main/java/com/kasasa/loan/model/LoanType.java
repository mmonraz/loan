package com.kasasa.loan.model;

public enum LoanType {
    STUDENT(0.00),
    AUTO(500.00),
    PERSONAL(750.00),
    MORTGAGE(1500.00);

    private double loanType;

    LoanType(double loanType) {
        this.loanType = loanType;
    }

    public double getLoanType(){
        return this.loanType;
    }
}
