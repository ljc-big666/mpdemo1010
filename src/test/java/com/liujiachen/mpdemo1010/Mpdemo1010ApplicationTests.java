package com.liujiachen.mpdemo1010;

import com.liujiachen.mpdemo1010.entity.User;
import com.liujiachen.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // 查询user表中的所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    // 添加操作
    @Test
    void addUser(){
        User user = new User();
        user.setName("东方不败");
        user.setAge(30);
        user.setEmail("mary@qq.com");

        //user.setCreateTime(new Date());
        //user.setUpdateTime(new Date());

        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }

    // 修改操作
    @Test
    void updateUser(){
        User user = new User();
        user.setId(1578248787153928194L);
        user.setAge(150);

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    // 测试乐观锁
    @Test
    public void testOptimisticLocker(){

        // 根据id查询数据
        User user = userMapper.selectById(1579030598352211969L);

        // 进行修改
        user.setAge(200);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    // 多个id批量查询
    @Test
    public void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }
}
