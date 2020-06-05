package com.wuhao.service.impl;


import com.wuhao.dao.UserMapper;
import com.wuhao.domain.User;
import com.wuhao.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        User user  = userMapper.queryUserById(1L);
        return user;
    }

}