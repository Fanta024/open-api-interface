package com.fanta.testinterface;

import com.fanta.fantaapicommon.model.entity.ClientUser;
import com.fanta.fantaclientsdk.client.FantaClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TestInterfaceApplicationTests {
    @Resource
    public FantaClient fantaClient;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        String namename = fantaClient.getNameByGet("namename");
        System.out.println(namename);
        String res2 = fantaClient.getNameByPost("namenameasdasd");
        System.out.println(res2);
        ClientUser user = new ClientUser();
        user.setUsername("123");
        String res3 = fantaClient.getUserNameByPost(user);
        System.out.println(res3);
    }
}
