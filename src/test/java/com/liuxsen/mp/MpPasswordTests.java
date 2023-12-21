package com.liuxsen.mp;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuxsen 2023/12/19
 */
@SpringBootTest
public class MpPasswordTests {
    @Test
    void pwdTest(){
        String randomKey = AES.generateRandomKey();
        System.out.println("key =>"+randomKey);

        String username = AES.encrypt("root", randomKey);
        System.out.println("username=>"+username);
        // 数据库
        String password = AES.encrypt("root", randomKey);
        System.out.println("password=>"+password);
    }
}
