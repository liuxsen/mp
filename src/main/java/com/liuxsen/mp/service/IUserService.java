package com.liuxsen.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuxsen.mp.domain.dto.PageDto;
import com.liuxsen.mp.domain.po.User;
import com.liuxsen.mp.domain.query.PageQuery;
import com.liuxsen.mp.domain.query.UserQuery;
import com.liuxsen.mp.domain.vo.UserVo;

/**
 * @author liuxsen 2023/12/18
 */
public interface IUserService  extends IService<User> {
    void deductBalance(Long userId, Integer money);
    UserVo queryUserAndAddressById(Long userId);
    PageDto<UserVo> queryUsersPage(UserQuery query);
}
