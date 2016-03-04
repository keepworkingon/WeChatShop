package com.bfl.kernel.dao.daoImpl;

import com.bfl.kernel.dao.UserMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by apple on 16/2/24.
 */
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public String getUserIdByName(int id) {
        return getSqlSession().selectOne("getUserIdByName",id);
    }
}
