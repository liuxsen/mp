package com.liuxsen.mp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuxsen 2023/12/19
 */
@SpringBootTest
public class BaseTest {
    @Test
    void testInt(){
        Integer num = 2147483647;
        System.out.println(Integer.MAX_VALUE);
    }
}
