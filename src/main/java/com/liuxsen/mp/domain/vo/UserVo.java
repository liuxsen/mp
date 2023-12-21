package com.liuxsen.mp.domain.vo;

import com.liuxsen.mp.domain.po.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author liuxsen 2023/12/18
 */


@Data
@ApiModel(description = "用户VO实体")
public class UserVo {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("详细信息")
    private UserInfo info;

    @ApiModelProperty("使用状态（1正常 2冻结）")
    private Integer status;

    @ApiModelProperty("账户余额")
    private Integer balance;

    @ApiModelProperty("地址")
    private List<AddressVo> addresses;
}