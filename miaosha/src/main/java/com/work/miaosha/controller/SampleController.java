package com.work.miaosha.controller;

import com.work.miaosha.Redis.RedisService;
import com.work.miaosha.Redis.UserKey;
import com.work.miaosha.pojo.User;
import com.work.miaosha.result.CodeMsg;
import com.work.miaosha.result.Result;
import com.work.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){

        model.addAttribute("name","Joshua");
        return "hello";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/db")
    @ResponseBody
    public Result<User> dbTest(){

        User user = userService.getById(1);
        return Result.success(user);
    }

    /**
     * 事务
     * @return
     */
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> tx(){

        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById,""+1,user);
        return Result.success(true);
    }
}
