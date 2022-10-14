package com.liujiachen.mpdemo1010;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liujiachen.mpdemo1010.entity.User;
import com.liujiachen.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
        user.setName("岳不群1");
        user.setAge(70);
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

    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        // 1、创建page对象
        // 传入两个参数，当前页 和 每页显示记录条数
        Page<User> page = new Page<>(2,3);
        // 调用mp分页查询的方法
        // 调用mp分页查询过程中，底层封装
        // 把分页所有的数据封装到page对象里面
        userMapper.selectPage(page,null);

        // 通过page对象获取分页数据
        System.out.println(page.getCurrent());// 当前页
        System.out.println(page.getRecords());// 每页数据list集合
        System.out.println(page.getSize());// 每页显示记录数
        System.out.println(page.getTotal());// 总记录数
        System.out.println(page.getPages());// 总页数

        System.out.println(page.hasNext());// 下一页
        System.out.println(page.hasPrevious());// 上一页
    }

    // 删除操作 物理删除
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1580487243657756674L);
        System.out.println(result);
    }

    // 批量删除
    @Test
    public void testDeleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(2L, 3L, 4L, 5L));
        System.out.println(result);
    }

    // mp实现复杂查询操作
    @Test
    public void testSelectQuery(){
        // 创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 通过QueryWrapper设置条件
        // ge、gt、le、lt 大于等于、大于、小于等于、小于
        // 查询age >= 40的记录
        // 第一个参数字段名字，第二个参数设置值
        //wrapper.gt("age",40);

        // eq、ne 等于、不等于]
        //wrapper.ne("name","mary");

        // between 查询年龄40-70之间的
        //wrapper.between("age",40,70);

        // like 模糊查询
        //wrapper.like("name","岳");

        // orderByDesc 降序 asc升序
        wrapper.orderByDesc("id");

        // last 后面加上的sql语句
        //wrapper.last("limit 1,3");

        // 指定要查询的列
        wrapper.select("name","age");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
