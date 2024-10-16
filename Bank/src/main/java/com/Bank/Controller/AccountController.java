package com.Bank.Controller;

import com.Bank.Entity.Account;
import com.Bank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //create Account
    @PostMapping("/create")
    public ResponseEntity<Account>createAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }
    //get account by accountNumber
    @GetMapping("/{id}")
    public Account getAccountByAccountNumber(@PathVariable  Long id){
        Account account = accountService.getAccountDetailsByAccountNumber(id);
        return account;
    }
    @GetMapping("/getAll")
    public List<Account> getAllAccountDetails(){
        return accountService.getAllAccountDetails();
    }

    // deposite money
    @PutMapping("/deposite/{accountNumber}/{amount}")
    public Account depositeAmount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account account = accountService.depositeAmount(accountNumber,amount);
        return account;
    }
    // withdraw Amount
    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public  Account withdrawAmount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account account = accountService.withdrawAmount(accountNumber,amount);
        return account;
    }

    // delete Account
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber){
        //  return new ResponseEntity<>(accountService.closeAccount(accountNumber),HttpStatus.NO_CONTENT);
        accountService.closeAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account Closed");
    }

}
