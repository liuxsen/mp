package com.liuxsen.mp.domain.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxsen 2023/12/19
 */
@Data
@ApiModel("分页实体")
public class PageQuery {
    @ApiModelProperty("页码")
    private Integer pageNo = 1;
    @ApiModelProperty("分页数量")
    private Integer pageSize = 20;
    @ApiModelProperty("排序字段")
    private String sortBy;
    @ApiModelProperty("是否正序")
    private Boolean isAsc;

    public <T> Page<T> toMpPage (OrderItem ...orders){
//        分页条件
        Page<T> page = Page.of(pageNo, pageSize);
        if(sortBy != null){
            page.addOrder(new OrderItem(sortBy, isAsc));
        }
        if(orders != null){
            page.addOrder(orders);
        }
        return page;
    }

    public <T> Page<T> toMpPage(String defaultSortBy, boolean isAsc){
        return this.toMpPage(new OrderItem(defaultSortBy, isAsc));
    }

    public <T> Page<T> toMpPageSortByCreateTime(){
        return toMpPage("create_time", false);
    }

    public <T> Page<T> toMpPageSortByUpdateTime(){
        return toMpPage("update_time", false);
    }
}
