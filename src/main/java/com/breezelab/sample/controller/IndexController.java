package com.breezelab.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    @ResponseBody
    // 로그인 된사람만
    public String user(){
        return "user";
    }

    @RequestMapping(value = "/admin" , method = RequestMethod.GET)
    @ResponseBody
    // 권한 로그인
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/manager" , method = RequestMethod.GET)
    @ResponseBody
    // 권한 로그인
    public String manager(){
        return "manager";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    @ResponseBody
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/join" , method = RequestMethod.GET)
    @ResponseBody
    // 회원 가입
    public String join(){
        return "join";
    }

    @RequestMapping(value = "/joinProc" , method = RequestMethod.GET)
    @ResponseBody
    public String joinProc(){
        return "회원가입 완료됨";
    }


}
