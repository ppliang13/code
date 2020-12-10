package com.ppl.redis;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

/**
 * @author ppliang
 * @date 2020/12/10 16:32
 */
@SpringBootTest
public class TestRedis {

    @Test
    public void testConnectRedis(){
        Jedis jedis = new Jedis("192.168.10.100", 6379);
        jedis.auth("root");

        jedis.select(1);
        String ping = jedis.ping();
        System.out.println("ping = " + ping);

        jedis.set("username", "罗卫飞");
        String username = jedis.get("username");
        System.out.println("username = " + username);
        if(jedis!=null){
            jedis.close();
        }

    }

    @Test
    public void test(){
        System.out.println("sadasdsa");
    }
}
