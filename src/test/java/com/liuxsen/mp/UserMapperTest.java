package com.liuxsen.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuxsen.mp.domain.po.Address;
import com.liuxsen.mp.domain.po.User;
import com.liuxsen.mp.domain.po.UserInfo;
import com.liuxsen.mp.mapper.UserMapper;
import com.liuxsen.mp.service.AddressService;
import com.liuxsen.mp.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxsen 2023/12/18
 */
@SpringBootTest(args = "--mpw.key=5bb0ec6afbff6dc9")
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserService userService;
    @Resource
    private AddressService addressService;

    @Test
    void testInsert(){
        User user = new User();
        user.setId(7L);
        user.setUsername("Lucy1111");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
//        "{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}"
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(24);
        user.setInfo(userInfo);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById(){
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }

    @Test
    void testQueryById(){
        User user = userMapper.queryById(5L);
        System.out.println("query user =>" + user);
    }

    @Test
    void testQueryWrapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id", "username", "info", "balance")
                .like("username", "o")
                // 大于等于 1000
                .ge("balance", 1000);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateByQueryWrapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", "Jack");
        // 1. user中非null字段都会座位set语句
        User user = new User();
        user.setBalance(2000);
        // UPDATE user SET balance=? WHERE (username = ?)
        userMapper.update(user, userQueryWrapper);
    }

    @Test
    void testUpdateWrapper(){
        // UPDATE user SET balance = balance - 200 WHERE (id IN (?,?,?))
        List<Long> ids = List.of(1L, 2L, 3L);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.setSql("balance = balance - 200")
                .in("id", ids); // WHERE id in (1,2,3)
        userMapper.update(null, userUpdateWrapper);
    }

    @Test
    void testLambdaQueryWrapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //  SELECT id,username,info,balance FROM user WHERE (username LIKE ? AND balance >= ?)
        userQueryWrapper.lambda()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println("lambda-query=>"+users);
    }

    @Test
    void testCustomWrapper(){
        List<Long> longs = List.of(1L, 2L, 3L);
        // UPDATE user set balance = balance - ? WHERE (id IN (?,?,?))
        QueryWrapper<User> in = new QueryWrapper<User>().in("id", longs);
        userMapper.deductBalanceByIds(100, in);
    }

    @Test
    void testQueryUserByIdAndAddr(){
        List<User> users = userMapper.queryUserByIdAndAddr(List.of(1L, 2L), "北京");
        System.out.println("user-xml=>"+users);
    }

    @Test
    void testQueryJoin(){
        // 1.准备自定义查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .in("u.id", List.of(1L, 2L, 4L))
                .eq("a.city", "北京");
        // 2.调用mapper的自定义方法
        List<User> users = userMapper.queryUserByWrapper(wrapper);

        users.forEach(System.out::println);
    }

    @Test
    void testSaveOneByOne(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            this.userService.save(buildUser(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    @Test
    void testSaveBatch(){
        ArrayList<User> users = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            // this.userService.save(buildUser(i))
            users.add(buildUser(i));
            if(i%1000 == 39){
                this.userService.saveBatch(users);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    private User buildUser(int i) {
        User user = new User();
        user.setUsername("user____" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
//        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(user.getCreateTime());
        return user;
    }

    @Test
    void testDeleteByLogic(){
        this.addressService.removeById(59L);
    }

    @Test
    void testRemovedQuery(){
        List<Address> addressList = this.addressService.list();
        System.out.println(addressList);
    }

    @Test
    void testEnum(){
        User user = this.userService.getById(1L);
        System.out.println(user);
    }

    @Test
    void testPage(){
        Page<User> page = this.userService.page(new Page<>(1, 2));
        System.out.println("total = " + page.getTotal());
        System.out.println("pages = " + page.getPages());
        List<User> records = page.getRecords();
        System.out.println(records);
    }

    @Test
    void testPageOrder(){
        Page<User> page = Page.of(2, 2);
        page.addOrder(new OrderItem("balance", true));
        Page<User> pageUser = this.userService.page(page);
        System.out.println(page.getRecords());
    }
}
