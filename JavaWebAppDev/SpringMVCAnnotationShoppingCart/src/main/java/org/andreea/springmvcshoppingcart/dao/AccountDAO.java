package org.andreea.springmvcshoppingcart.dao;

import org.andreea.springmvcshoppingcart.entity.Account;

import java.util.List;

public interface AccountDAO {

    public Account findAccount(String userName );

}