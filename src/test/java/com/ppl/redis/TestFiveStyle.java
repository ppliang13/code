package com.ppl.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ppliang
 * @date 2020/12/10 19:19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFiveStyle {

    //线程池
    @Autowired
    private JedisPool jedisPool;
    private Jedis jedis=null;
    /**
     * description: 初始化方法
     *      jedis从线程池获取对象
     * @param
     * @return void
     */
    @Before
    public void init(){
        jedis=jedisPool.getResource();
    }
    /**
     * description: 销毁方法
     *      判断jedis是否为空，不为空销毁
     * @param
     * @return void
     */
    @After
    public void destroy(){
        if(jedis!=null){
            jedis.close();
        }
    }

    /**
     * description: 测试是否连接上redis
     *
     * @param
     * @return void
     */
    @Test
    public void testConnectRedis(){
        String ping = jedis.ping();
        System.out.println("ping = " + ping);
    }


}
