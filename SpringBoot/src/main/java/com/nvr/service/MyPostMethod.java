package com.nvr.service;

import com.nvr.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/post")
@Api(value = "这是所有的post方法测试")
public class MyPostMethod {
    //这是一个登录的请求
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "这是一个登录的请求", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "ssn", required = true) String ssn,
                        @RequestParam(value = "password", required = true) String password) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        if (ssn.equals("zhangsan") && password.equals("1234")) {
            return "恭喜登录成功。。。";
        }
        return "密码或账号错误";
    }

    @RequestMapping(value = "/login/with/cookies", method = RequestMethod.POST)
    @ApiOperation(value = "带cookies登录请求", httpMethod = "POST")
    public String loginWithCookies(HttpServletRequest request,
                                   @RequestBody User u) {
        User user;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")
                    && u.getUsername().equals("zhangsan") && u.getPassword().equals("1234")) {
                user = new User();
                user.setUsername(u.getUsername());
                user.setPassword(u.getPassword());
                user.setName("张三");
                user.setSex("man");
                user.setAge(23);
                return "登录成功,用户信息:" + user.toString();
            }
        }
        return "参数不正确";
    }
}
