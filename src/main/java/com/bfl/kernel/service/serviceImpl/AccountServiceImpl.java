package com.bfl.kernel.service.serviceImpl;

import com.bfl.kernel.dao.AccountMapper;
import com.bfl.kernel.entity.Account;
import com.bfl.kernel.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by apple on 16/3/8.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{
    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account getAccount() {
        Account accountFromDB =  accountMapper.getAccount();
        if (accountFromDB != null) {
            logger.info("帐号 acc_num={} 已被消费", accountFromDB.getAcc_num());
        }
        return accountFromDB;
    }

    
    /*
    @Override
    public int setAccountMarkToZero(int id) {
        int updateRowNum = accountMapper.setAccountMarkToZero(id);
        if(updateRowNum < 1){
            logger.error("帐号消费之后,mark位置零出现错位,请及时修复!");
        }
        return updateRowNum;
    }
    */
}
