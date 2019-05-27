package com.nvr.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/", description = "这是测试所有的get请求接口")
public class MyGetMethod {
    @RequestMapping(value = "/getcookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法获取cookie", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜，获取到了cookie";
    }

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带cookie才能访问成功", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "当前请求缺少cookies信息,请携带cookies访问";
        }
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "携带cookies信息访问成功！！！";
            }
        }
        return "请携带正确Cookies访问";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url: key=value&key=value
     * 我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param", method = RequestMethod.GET)
    @ApiOperation(value = "带参数的第一种形式get请求", httpMethod = "GET")
    public Map<String, Integer> getWithParam1(@RequestParam Integer start,
                                              @RequestParam Integer end) {
        Map<String, Integer> items = new HashMap<>();
        items.put("面包", 5);
        items.put("可乐", 3);
        items.put("泡面", 6);
        return items;
    }
    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url: /start/end
     * 我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value="带参数的第二种形式get请求",httpMethod ="GET" )
    public Map<String,Integer> getWithParam2(@PathVariable Integer start,
                                             @PathVariable Integer end){
        Map<String,Integer> items=new HashMap<>();
        items.put("面包", 5);
        items.put("雪碧", 3);
        items.put("泡面", 6);
        return items;
    }
}
