package com.bfl.kernel.service.serviceImpl;

import com.bfl.kernel.dao.AccountMapper;
import com.bfl.kernel.entity.Account;
import com.bfl.kernel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by apple on 16/3/8.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account getAccount() {
        return accountMapper.getAccount();
    }
}
