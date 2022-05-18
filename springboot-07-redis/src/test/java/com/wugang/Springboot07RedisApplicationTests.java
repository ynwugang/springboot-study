package com.wugang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Springboot07RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        // redisTemplate 操作不同的数据类型，api和指令是一样的
        // opsForValue() 操作字符串 类似String
        // opsForList() 操作List 类似List
        // opsForSet()
        // opsForHash()
        // opsForZSet()
        // opsForGeo()
        // opsForHyperLogLog()


        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事物和基本的CRUD

        // 获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        redisTemplate.opsForValue().set("mykey", "狂神说Java");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

}
