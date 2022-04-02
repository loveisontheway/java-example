package com.muxi.java.example.tree;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree Node
 *
 * @author jjl on 2022/3/31
 */
abstract class TreeUtil {
    /**
     * 把列表转换为树结构
     *
     * @param originalList 原始list数据
     * @param keyName      唯一标识（主键id）
     * @param parentName   父标识（父id）
     * @return 组装后的集合
     */
    public static <T> List<T> getTree(List<T> originalList, String keyName, String parentName) {
        String childrenFieldName = "children";

        // 获取根节点，即找出父节点为空的对象
        List<T> topList = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i++) {
            T t = originalList.get(i);
            String parentId = null;
            try {
                parentId = BeanUtils.getProperty(t, parentName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (StringUtils.isBlank(parentId) || "0".equals(parentId)) {
                topList.add(t);
            }
        }

        // 将根节点从原始list移除，减少下次处理数据
        originalList.removeAll(topList);

        // 递归封装树
        try {
            fillTree(topList, originalList, keyName, parentName, childrenFieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topList;
    }

    /**
     * 封装树
     *
     * @param parentList        要封装为树的父对象集合
     * @param originalList      原始list数据
     * @param keyName           唯一标识（主键id）
     * @param parentName        模型中作为parent字段名称
     * @param childrenFieldName 模型中作为children的字段名称
     */
    public static <T> void fillTree(List<T> parentList, List<T> originalList, String keyName, String parentName, String childrenFieldName) throws Exception {
        for (int i = 0; i < parentList.size(); i++) {
            List<T> children = fillChildren(parentList.get(i), originalList, keyName, parentName, childrenFieldName);
            if (children.isEmpty()) {
                continue;
            }
            originalList.removeAll(children);
            fillTree(children, originalList, keyName, parentName, childrenFieldName);
        }
    }

    /**
     * 封装子对象
     *
     * @param parent            父对象
     * @param originalList      待处理对象集合
     * @param keyName           唯一标识（主键id）
     * @param parentName        模型中作为parent字段名称
     * @param childrenFieldName 模型中作为children的字段名称
     */
    public static <T> List<T> fillChildren(T parent, List<T> originalList, String keyName, String parentName, String childrenFieldName) throws Exception {
        List<T> childList = new ArrayList<>();
        String parentId = BeanUtils.getProperty(parent, keyName);
        for (int i = 0; i < originalList.size(); i++) {
            T t = originalList.get(i);
            String childParentId = BeanUtils.getProperty(t, parentName);
            if (parentId.equals(childParentId)) {
                childList.add(t);
            }
        }
        if (!childList.isEmpty()) {
            FieldUtils.writeDeclaredField(parent, childrenFieldName, childList, true);
        }
        return childList;
    }
}
