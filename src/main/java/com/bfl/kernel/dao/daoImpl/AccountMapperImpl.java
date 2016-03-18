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
            if(accountFromDB != null){
                int id = accountFromDB.getId();
                int updateRowNum = setAccountMarkToZero(id);
                if(updateRowNum < 1){
                    logger.error("帐号消费之后,mark位置零出现错位,请及时修复!");
                }
            }
            return accountFromDB;

    }

    @Override
    public int setAccountMarkToZero(int id) {
        return getSqlSession().update("setAccountMarkToZero",id);
    }
}
