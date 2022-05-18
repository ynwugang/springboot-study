package com.wugang.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service  //放到spring容器中
public class UserService {
    //想拿到provide-service提供的票,要去注册中心拿
    @Reference //引用, pom坐标/可以定义路径相同的接口名
    TicketService TICKET_SERVICE;


    public void byTicket(){
        String ticket = TICKET_SERVICE.getTicket();
        System.out.println("在注册中心拿到=>" + ticket);
    }
}
