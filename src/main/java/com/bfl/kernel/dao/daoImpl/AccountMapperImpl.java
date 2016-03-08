package com.bfl.kernel.dao.daoImpl;

import com.bfl.kernel.dao.AccountMapper;
import com.bfl.kernel.entity.Account;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by apple on 16/3/8.
 */
public class AccountMapperImpl extends SqlSessionDaoSupport implements AccountMapper{

    @Override
    public Account getAccount() {

            Account accountFromDB =  getSqlSession().selectOne("getAccount");
            if(accountFromDB == null){
                accountFromDB = new Account();
            }
            return accountFromDB;

    }

    @Override
    public int setAccountMarkToZero(int id) {
        return getSqlSession().update("setAccountMarkToZero",id);
    }
}
