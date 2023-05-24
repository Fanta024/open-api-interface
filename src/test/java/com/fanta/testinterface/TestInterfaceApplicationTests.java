package com.fanta.testinterface;

import com.fanta.testinterface.client.FantaClient;
import com.fanta.testinterface.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestInterfaceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        FantaClient fantaClient = new FantaClient("fanta", "asdqwe");
        String namename = fantaClient.getNameByGet("namename");
        System.out.println(namename);
        String res2 = fantaClient.getNameByPost("namenameasdasd");
        System.out.println(res2);
        User user = new User();
        user.setUsername("123");
        String res3 = fantaClient.getUserNameByPost(user);
        System.out.println(res3);
    }
}
