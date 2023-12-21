package com.liuxsen.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.liuxsen.mp.domain.dto.PageDto;
import com.liuxsen.mp.domain.po.Address;
import com.liuxsen.mp.domain.po.User;
import com.liuxsen.mp.domain.query.UserQuery;
import com.liuxsen.mp.domain.vo.AddressVo;
import com.liuxsen.mp.domain.vo.UserVo;
import com.liuxsen.mp.mapper.UserMapper;
import com.liuxsen.mp.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxsen 2023/12/18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public void deductBalance(Long userId, Integer money) {
        User user = this.getById(userId);
        if(user == null || user.getStatus().getValue() == 2){
            throw new RuntimeException("用户状态异常");
        }
        if(user.getBalance() < money){
            throw new RuntimeException("用户余额不足");
        }
        // this.baseMapper.deductBalanceById(money, userId);
        int newBalance = user.getBalance() - money;

        this.lambdaUpdate()
                .set(User::getBalance, newBalance)
                .set(newBalance <= 0, User::getStatus, 2) // 动态判断是否更新status
                .eq(User::getId, userId)
                .eq(User::getBalance, user.getBalance()) // 乐观锁？？？
                .update();
    }

    @Override
    public UserVo queryUserAndAddressById(Long userId) {
        User user = this.getById(userId);
        if(user == null){
            return null;
        }
//        2. 查询用户地址
        List<Address> addressList = Db.lambdaQuery(Address.class)
                .eq(Address::getUserId, userId)
                .list();
        UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
        userVo.setAddresses(BeanUtil.copyToList(addressList, AddressVo.class));
        return userVo;
    }

    @Override
    public PageDto<UserVo> queryUsersPage(UserQuery query) {
        Page<User> page = query.toMpPageSortByCreateTime();
//        if(query.getSortBy() != null){
//            page.addOrder(new OrderItem(query.getSortBy(), query.getIsAsc()));
//        } else {
//            page.addOrder(new OrderItem("update_time", false));
//        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(query.getName() != null, User::getUsername, query.getName())
                .le(query.getMaxBalance() != null, User::getBalance, query.getMaxBalance())
                .gt(query.getMinBalance() != null, User::getBalance, query.getMinBalance())
        ;

        this.page(page, wrapper);
//        List<User> records = page.getRecords();
//        if(records == null || records.size() == 0){
//            return new PageDto<>(page.getTotal(), page.getPages(), Collections.emptyList());
//        }
//        List<UserVo> userVos = BeanUtil.copyToList(records, UserVo.class);
//        return new PageDto<>(page.getTotal(), page.getPages(), userVos);
        return PageDto.of(page, UserVo.class);
    }
}
