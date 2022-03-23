package com.example.springboot.tools.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/10/29 13:55
 * @version: v1.0
 */
@Setter
@Getter
@ToString
public class ResourceListVO implements TreeNode<String> {

    private Long id;

    // 节点code
    private String enName;

    // 节点名称
    private String cnName;

    // 父节点code
    private String abvenName;

    private List<ResourceListVO> children;

    @Override
    public String id() {
        return this.enName;
    }

    @Override
    public String parentId() {
        return this.abvenName;
    }

    @Override
    public boolean root() {
        return Objects.equals(this.abvenName, null);
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }

    @Override
    public List<? extends TreeNode<String>> getChildren() {
        return this.children;
    }
}
