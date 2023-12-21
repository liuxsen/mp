package com.liuxsen.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liuxsen.mp.domain.dto.PageDto;
import com.liuxsen.mp.domain.dto.UserFormDto;
import com.liuxsen.mp.domain.po.User;
import com.liuxsen.mp.domain.query.UserQuery;
import com.liuxsen.mp.domain.vo.UserVo;
import com.liuxsen.mp.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuxsen 2023/12/18
 */

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    IUserService userService;

    @PostMapping
    @ApiOperation("新增用户")
    public void saveUser(@RequestBody UserFormDto userFormDto){
        userFormDto.setId(null);
        User user = BeanUtil.copyProperties(userFormDto, User.class);
        this.userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public  void removeUserById( @PathVariable Long id){
        this.userService.removeById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户")
    public UserVo queryUserById(@PathVariable Long id){
        User byId = this.userService.getById(id);
        return BeanUtil.copyProperties(byId, UserVo.class);
    }

    @GetMapping
    @ApiOperation("根据id集合查询用户")
    public List<UserVo> queryUserByIds(
            @RequestParam("ids") @ApiParam(value = "用户id列表", example = "1,2,3") List<String> ids){
        List<User> users = this.userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVo.class);
    }

    @PutMapping("{id}/deduction/{money}")
    @ApiOperation("扣减用户金额")
    public void deductBalance(@PathVariable("id") Long id, @PathVariable("money") Integer money){
        this.userService.deductBalance(id, money);
    }

    @GetMapping("/{id}/with_address")
    @ApiOperation("根据用户id查询用户")
    public UserVo getUserById( @PathVariable("id") Long id){
        return this.userService.queryUserAndAddressById(id);
    }

    @GetMapping("/page/list")
    @ApiOperation("分页查询用户列表")
    public PageDto<UserVo> queryUserPage(UserQuery query){
        return this.userService.queryUsersPage(query);
    }

    @GetMapping("/list")
    @ApiOperation("集合查询用户")
    public List<UserVo> queryUsers(UserQuery query){
        String name = query.getName();
        Integer status = query.getStatus();
        Integer minBalance = query.getMinBalance();
        Integer maxBalance = query.getMaxBalance();
        LambdaQueryWrapper<User> wrapper= new QueryWrapper<User>().lambda()
                .like(name != null, User::getUsername, name)
                .eq(status !=null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance);
        List<User> list = userService.list(wrapper);
        return BeanUtil.copyToList(list, UserVo.class);
    }

    @GetMapping("/list2")
    @ApiOperation("集合查询用户")
    public List<UserVo> queryUsers2(UserQuery query){
        String name = query.getName();
        Integer status = query.getStatus();
        Integer minBalance = query.getMinBalance();
        Integer maxBalance = query.getMaxBalance();
        List<User> list = this.userService.lambdaQuery().like(name != null, User::getUsername, name).eq(status != null, User::getStatus, status).ge(minBalance != null, User::getBalance, minBalance).le(maxBalance != null, User::getBalance, maxBalance).list();
        return BeanUtil.copyToList(list, UserVo.class);
    }
}
