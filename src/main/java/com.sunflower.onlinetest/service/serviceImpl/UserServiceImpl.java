package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.User;
import com.sunflower.onlinetest.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    public User findById(Integer userId) {
        return userDAO.findById(userId);
    }
}
