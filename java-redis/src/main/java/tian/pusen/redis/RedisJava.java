package tian.pusen.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tianpusen on 2017/3/17.
 */
public class RedisJava {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());

        //set the data in redis string
        jedis.set("tutorial-name", "Redis tutorial");
        System.out.println("Stored string in redis:: "+ jedis.get("tutorialname"));

        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }

        // 获取数据并输出
        Set<String> set = jedis.keys("*");

        Map<String, String> map = new HashMap<String, String>();
        // 添加数据
        map.put("name","Usher");
        map.put("age","24");
        map.put("sex", "male");
        // 添加到redis中

    }
}
