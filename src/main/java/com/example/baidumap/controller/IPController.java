package com.example.baidumap.controller;

import com.example.baidumap.util.AddressUtils;
import com.example.baidumap.util.GetPlaceByIp;
import com.example.baidumap.util.NetworkUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * IPController:测试IP真是地址
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/15
 */
@RestController
public class IPController {
    @Autowired
    private AddressUtils addressUtils;

    @RequestMapping("/getip")
    public String getIPAddr(HttpServletRequest request) throws Exception {
        String ipAddress = NetworkUtil.getIpAddress(request);
        System.out.println("访问者真是IP " + ipAddress);
        Object result ;
        try {
            result = addressUtils.getAddresses("ip=" + ipAddress, "utf-8");
            System.out.println("访问者的详细信息(来自淘宝API提供): " + result);
        } catch (UnsupportedEncodingException e) {
            result = GetPlaceByIp.readJsonFromUrl("http://api.map.baidu.com/location/ip?ip=" + ipAddress + "&ak=HgiEfvBOOuFPAzAM94UZmx0IAtHABP9c");
            System.out.println("访问者的详细信息(淘宝异常,百度API提供): " + result);
        } catch (Exception e) {
            return "淘宝和百度API都挂了,访问不了了";
        }
        return "请求的真实IP: " + ipAddress + " 返回结果:" + result;

    }

}
