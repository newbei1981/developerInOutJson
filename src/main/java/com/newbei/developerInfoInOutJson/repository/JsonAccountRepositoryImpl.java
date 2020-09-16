package com.newbei.developerInfoInOutJson.repository;

import com.newbei.developerInfoInOutJson.model.Account;
import com.newbei.developerInfoInOutJson.model.AccountStatus;

import java.util.List;

import static com.newbei.developerInfoInOutJson.json.JsonFilePath.ACCOUNT_JSON_PATH;
import static com.newbei.developerInfoInOutJson.json.JsonParser.readJsonAccountFile;
import static com.newbei.developerInfoInOutJson.json.JsonParser.writeJsonFile;

public class JsonAccountRepositoryImpl implements AccountRepository{

    private List<Account> accounts;

    private long generateId() {
        accounts = readJsonAccountFile();
        return (long) (accounts.get(accounts.size() - 1).getId() + 1);
    }

    @Override
    public Account save(Account account){
        account.setId(generateId());
        accounts = getAll();
        accounts.add(account);
        writeJsonFile(accounts, ACCOUNT_JSON_PATH);
        return account;
    }

    @Override
    public Account update(Account account) {
        accounts = getAll();
        int index;
        Account updetedAccount;
        for (Account acc:accounts) {
            if (acc.getId() == account.getId()) {
                updetedAccount = acc;
                index = accounts.indexOf(acc);
                accounts.set(index, account);
                writeJsonFile(accounts, ACCOUNT_JSON_PATH);
                return updetedAccount;
            }
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        return readJsonAccountFile();
    }

    @Override
    public Account getById(Long aLong) {
        accounts = getAll();
        for (Account acc:accounts){
            if (acc.getId()==aLong) return acc;
        }
        return null;
    }

    @Override
    public Account deleteById(Long aLong) {
        accounts = getAll();
        for (Account account:accounts){
            if (account.getId() == aLong) {
                account.setPassword("");
                account.setLogin("");
                account.setAccountStatus(AccountStatus.DELETED);
                update(account);
                writeJsonFile(accounts,ACCOUNT_JSON_PATH);
                return account;
            }
        }
        return null;
    }

    @Override
    public long authentication(String login, String password){
        accounts = getAll();
        for (Account account:accounts){
            if (!(account.getAccountStatus().equals(AccountStatus.DELETED))&&(account.getLogin().equals(login)&&(account.getPassword().equals(password)))){
                return account.getId();
            }
        }
        return -1;
    }
}
