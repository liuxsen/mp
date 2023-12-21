package com.liuxsen.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuxsen.mp.domain.po.User;
import com.liuxsen.mp.domain.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liuxsen 2023/12/18
 */
public interface UserMapper extends BaseMapper<User> {
    User queryById(Long id);

    List<User> queryUserByIdAndAddr(List<Long> userIds, String city);

    @Select("UPDATE user set balance = balance - #{money} ${qw.customSqlSegment}")
    void deductBalanceByIds(@Param("money") int money, @Param("qw") QueryWrapper<User> wrapper);

    @Update("UPDATE user set balance = balance - #{money} where id = #{userId}")
    void deductBalanceById(@Param("money") Integer money, @Param("userId") Long userId);

//     通过自定义mapper，实现join
    @Select("SELECT u.* FROM user u left join address a on u.id = a.user_id ${ew.customSqlSegment}")
    List<User> queryUserByWrapper(@Param("ew") QueryWrapper<User> wrapper);
}
