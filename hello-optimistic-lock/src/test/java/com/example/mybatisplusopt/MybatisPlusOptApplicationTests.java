package com.example.mybatisplusopt;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusOptApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void insert() {
        User user = new User();
        user.setName("Helen");
        user.setAge(16);
        user.setEmail("7332@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    void update() {
        User user = userMapper.selectById(1);
        user.setName("amber");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void delete() {
        // 公司里一般不这么用，一般都是逻辑删除
        userMapper.deleteById(3L);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "helen");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectByPage() {
        Page<User> page = new Page(1, 2);
        userMapper.selectPage(page, null);
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getPages());//总页数
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }

    @Test
    void insert2() {
        User user = new User();
        user.setName("grey");
        user.setAge(19);
        user.setEmail("7332@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    void update2() {
        User user = userMapper.selectById(1479008189003886594L);
        user.setName("amber133");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void testOpt() {
        User user = userMapper.selectById(2L);
        user.setName("love");
        user.setEmail("123@qq.com");
        User user2 = userMapper.selectById(2L);
        user2.setName("lover222");
        user2.setEmail("123@qq.com");
        userMapper.updateById(user2);
        //如果没有乐观锁就会覆盖user2的值,这里加入乐观锁不会覆盖user2的值
        userMapper.updateById(user);
    }

}
