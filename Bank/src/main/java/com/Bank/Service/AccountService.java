package com.Bank.Service;

import com.Bank.Entity.Account;

import java.util.List;

public interface AccountService {

    public Account createAccount(Account account);
    public Account getAccountDetailsByAccountNumber(Long accountNumber);
    public List<Account> getAllAccountDetails();
    public Account depositeAmount(Long accountNUmber, Double amount);
    public Account withdrawAmount(Long accountNUmber, Double amount);
    public void closeAccount(Long accountNumber);
}
