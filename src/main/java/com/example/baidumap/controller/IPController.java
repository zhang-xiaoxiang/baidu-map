package com.example.baidumap.controller;

import com.example.baidumap.util.AddressUtils;
import com.example.baidumap.util.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public String getIPAddr(HttpServletRequest request) throws IOException {
        String ipAddress = NetworkUtil.getIpAddress(request);
        System.out.println("访问者真是IP "+ipAddress);
        System.out.println("访问者的详细信息: "+addressUtils.getAddresses("ip="+ipAddress, "utf-8"));
        return ipAddress;

    }

}
