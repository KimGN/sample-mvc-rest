package com.breezelab.sample.controller;

import com.breezelab.sample.mappers.UserMapper;
import com.breezelab.sample.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {

    private UserMapper mapper;

    @Autowired
    public UserController(UserMapper mapper){
        this.mapper = mapper;
    }

    @GetMapping("/user/{id}")
    public UserModel getUserModel(@PathVariable("id") String id){
        return mapper.getUserModel(id);
    }

    @GetMapping("user/all")
    public List<UserModel> getUserModelList() throws Exception{ return mapper.getUserModelList(); }



    // 우선 만들고 서비스로 쪼개 보자
    @GetMapping("/singUpApi")
    public void insertUser(@RequestParam("account") String account, @RequestParam("password") String password){
        mapper.insertUserModel(account, password);
    }

    @GetMapping("/join")
    public ModelAndView join_html(){

        ModelAndView model = new ModelAndView();

        model.setViewName("join");

        return model;
    }






}
