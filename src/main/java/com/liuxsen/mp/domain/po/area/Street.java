package com.liuxsen.mp.domain.po.area;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxsen 2023/12/21
 */
@Data
@ApiModel("乡")
public class Street {
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("乡名称")
    private String name;
    @ApiModelProperty("县code")
    private String areaCode;
    @ApiModelProperty("市code")
    private String cityCode;
    @ApiModelProperty("省code")
    private String provinceCode;
}
