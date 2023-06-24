package com.learning.entity;


import java.io.Serializable;
import java.util.List;

import com.learning.utils.TreeUtils.TreeNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Department)实体类
 *
 * @author makejava
 * @since 2023-06-24 17:42:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable, TreeNode {

    private static final long serialVersionUID = 580201858188810698L;

    private Integer id;

    private String name;

    private Integer parentId;

    private List<Department> children;

    @Override
    public Object id() {
        return id;
    }

    @Override
    public Object parentId() {
        return parentId;
    }

    @Override
    public boolean root() {
        return parentId == 0;
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

    @Override
    public List getChildren() {
        return this.children;
    }
}

