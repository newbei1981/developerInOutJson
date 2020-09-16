package com.newbei.developerInfoInOutJson.controller;

import com.newbei.developerInfoInOutJson.model.Account;
import com.newbei.developerInfoInOutJson.model.AccountStatus;
import com.newbei.developerInfoInOutJson.repository.AccountRepository;
import com.newbei.developerInfoInOutJson.repository.JsonAccountRepositoryImpl;

import static com.newbei.developerInfoInOutJson.view.DeveloperView.*;

public class AccountController {
    private AccountRepository accountRepository = new JsonAccountRepositoryImpl();

    public Account saveAccount(){
        Account account = new Account();
        account.setLogin(saveOrUpdateRecord("Login"));
        account.setPassword(saveOrUpdateRecord("Password"));
        account.setAccountStatus(AccountStatus.ACTIVE);
        return accountRepository.save(account);
    }

    public Account updateAccountLogin(Account account){
        account.setLogin(saveOrUpdateRecord("Login",account.getLogin()));
        accountRepository.update(account);
        return account;
    }
    public Account updateAccountPassword(Account account){
        account.setPassword(saveOrUpdateRecord("Account",account.getPassword()));
        accountRepository.update(account);
        return account;
    }

    //only for Administrator methods
    private Account updateAccountStatus(Account account){
        account.setPassword(saveOrUpdateRecord("AccountStatus", account.getAccountStatus().toString()));
        accountRepository.update(account);
        return account;
    }
}
