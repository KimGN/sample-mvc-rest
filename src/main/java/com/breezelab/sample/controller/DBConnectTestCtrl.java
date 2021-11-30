package com.breezelab.sample.controller;

import com.breezelab.sample.dao.UserDAO;
import com.breezelab.sample.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DBConnectTestCtrl {


    private UserDAO userDao;

    public DBConnectTestCtrl(UserDAO userDao){
        this.userDao = userDao;
    }

    @GetMapping("/user")
    public List<UserDTO> UserPage(){
        System.out.println(userDao);
        return userDao.getTestData();
    }

    @GetMapping("/test")
    public String testPage(){
        return "TEST PAGE";
    }

}
