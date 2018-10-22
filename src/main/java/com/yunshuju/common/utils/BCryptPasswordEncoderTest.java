package com.yunshuju.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by yangyibo on 17/3/2.
 */
public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("123:  " + encoder.encode("123"));
        if (encoder.matches("123", "$2a$10$cyN6nyMx1Bh9g9BV7G9jGellbYrzlx22e0NIXtYgBuIlJfa7v13mS")) {
            System.out.println("123: true");
        }

        System.out.println("------------华丽的分割线-----------------------");
        String Md5Password = MD5Util.encode("123");
        System.out.println("123Md5Password:  " + Md5Password);
        System.out.println("123:  " + encoder.encode(Md5Password));
        if (encoder.matches(Md5Password, "$2a$10$cyN6nyMx1Bh9g9BV7G9jGellbYrzlx22e0NIXtYgBuIlJfa7v13mS")) {
            System.out.println("123Md5Password: true");
        }
    }

}
