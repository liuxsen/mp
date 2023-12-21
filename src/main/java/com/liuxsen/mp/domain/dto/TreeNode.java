package com.liuxsen.mp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxsen 2023/12/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    String code;
    String parentCode;
    List<TreeNode> children = new ArrayList<>();

    public TreeNode(String code, String parentCode){
        this.code = code;
        this.parentCode = parentCode;
    }
}
