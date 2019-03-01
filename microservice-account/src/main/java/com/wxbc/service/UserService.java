package com.wxbc.service;

import com.wxbc.pojo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public UserInfo getUserInfo(String name) {
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setPassword("123");
        user.setRole("tl");
        user.setTel("138888888");
        return user;
    }
}
