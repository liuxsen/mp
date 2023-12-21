package com.liuxsen.mp.domain.po.area;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxsen 2023/12/21
 */
@Data
@ApiModel("省")
public class Province {
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("省名称")
    private String name;
}
