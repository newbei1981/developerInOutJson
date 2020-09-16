package com.newbei.developerInfoInOutJson.repository;

import com.newbei.developerInfoInOutJson.model.Account;

public interface AccountRepository extends GenericRepository<Account,Long> {
    long authentication(String login, String password);
}