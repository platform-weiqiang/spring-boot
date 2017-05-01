package com.springboot.security.service;

import com.springboot.security.dao.UserDao;
import com.springboot.security.entity.SysUser;
import com.springboot.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;

    public SysUser findByName(String userName) {
        return userRepository.findByName(userName);
    }

    public void update(SysUser su) {
        userDao.update(su);
    }
}
