package com.liuxsen.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuxsen 2023/12/19
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
    NORMAL(1, "正常"),
    FREEZE(2, "冻结"),
    ;
    @EnumValue
    private final int value;
    @JsonValue
    private final String desc;
}
