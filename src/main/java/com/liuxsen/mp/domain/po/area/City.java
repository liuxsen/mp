package com.liuxsen.mp.domain.po.area;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxsen 2023/12/21
 */
@Data
@ApiModel("市")
public class City {
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("市名称")
    private String name;
    @ApiModelProperty("省份code")
    private String provinceCode;
}
