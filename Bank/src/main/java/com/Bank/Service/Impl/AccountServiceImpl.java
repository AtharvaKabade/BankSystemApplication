package com.Bank.Service.Impl;

import com.Bank.Entity.Account;
import com.Bank.Repository.AccountRepository;
import com.Bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account createAccount(Account account) {
        return  accountRepository.save(account);

    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account account_found = account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccount = accountRepository.findAll();
        return listOfAccount;
    }

    @Override
    public Account depositeAmount(Long accountNumber, Double amount) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isEmpty()){
            throw  new RuntimeException("Account is not present");
        }
        Account currentAccount = account.get();
        Double totalBalance = currentAccount.getAccount_balance()+amount;
        currentAccount.setAccount_balance(totalBalance);
        accountRepository.save(currentAccount);
        return currentAccount;
    }

    @Override
    public Account withdrawAmount(Long accountNUmber, Double amount) {
       Optional<Account> account=  accountRepository.findById(accountNUmber);
       if(account.isEmpty()){
           throw new RuntimeException("Account is not present");
       }
       Account currentAccount = account.get();
       Double totalBalance = currentAccount.getAccount_balance() - amount;
       currentAccount.setAccount_balance(totalBalance);
       accountRepository.save(currentAccount);
        return currentAccount;
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber); // check wether account is present or not
        accountRepository.deleteById(accountNumber);
    }
}
