package com.pp.auth.dao;

import oauth2.entity.Account;

public interface AccountDao {
    Account getAccountByUserName(String userName);
}