package com.liuxsen.mp.domain.po;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
* 
* @TableName address
*/

@TableName("address")
@Data
public class Address implements Serializable {
    private Long id;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 省
    */
    private String province;
    /**
    * 市
    */
    private String city;
    /**
    * 县/区
    */
    private String town;
    /**
    * 手机
    */
    private String mobile;
    /**
    * 详细地址
    */
    private String street;
    /**
    * 联系人
    */
    private String contact;
    /**
    * 是否是默认 1默认 0否
    */
    private Boolean isDefault;
    /**
    * 备注
    */
    private String notes;
    /**
    * 逻辑删除
    */
    private Boolean deleted;

}
