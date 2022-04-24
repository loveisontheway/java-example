package com.muxi.java.example.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    /**
     * 构建父菜单
     */
    public List<Menu> getParentNode(List<Menu> menuList) {

        // 声明一个装载节点的父容器
        List<Menu> rootNode = new ArrayList<>();
        // 遍历所有的节点
        for (Menu menu : menuList) {
            // 判断是不是父菜单
            if (menu.getParentId() == 0) {
                rootNode.add(menu);
            }
        }

        // 为父节点创建子节点
        for (Menu menu : rootNode) {
            // 创建子节点
            List<Menu> sonNode = buildSonNode(menuList, menu.getParentId());
            // 为每一个父节点赋值子节点
            menu.setChildren(sonNode);
        }
        return rootNode;

    }

    /**
     * 建立子节点
     */
    public List<Menu> buildSonNode(List<Menu> menuList, Integer id) {

        // 装载菜单的容器
        List<Menu> childrenNode = new ArrayList<>();
        for (Menu menu : menuList) {
            // 如果当前菜单的父ID 和 父菜单的ID 一致，则说明此子菜单 是 该父菜单的子菜单
            if (menu.getParentId().equals(id)) {
                childrenNode.add(menu);
            }
        }

        // 继续为子菜单 构建 子菜单
        for (Menu menu : childrenNode) {
            menu.setChildren(buildSonNode(menuList, menu.getParentId()));
        }

        // 如果菜单的个数为0，则说明没有子菜单
        if (childrenNode.size() == 0) {
            return null;
        }
        return childrenNode;
    }
}