package com.bfl.kernel.service.serviceImpl;

import com.bfl.kernel.dao.UserMapper;
import com.bfl.kernel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by apple on 16/2/24.
 */
@Service("userService")
public class IUserServiceImpl implements IUserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserById(int userId){
        return this.userMapper.getUserIdByName(userId);
    }
}
