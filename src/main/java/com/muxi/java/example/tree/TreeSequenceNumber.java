package com.muxi.java.example.tree;

import com.alibaba.fastjson.JSON;
import com.muxi.java.example.domain.TreeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构生成序号
 *
 * 1.1
 * 1.1.1
 * 1.1.2
 * 1.2
 * 1.2.1
 * 1.2.1
 */
public class TreeSequenceNumber {

    public static void main(String[] args) {
        TreeSequenceNumber treeSequenceNumber = new TreeSequenceNumber();
        treeSequenceNumber.createSeqNum();
    }

    void createSeqNum() {
        // 组装数据
        List<TreeVO> treeList = createData();

        // 根节点生成序号
        for (int i = 0; i < treeList.size(); i++) {
            TreeVO treeVO = treeList.get(i);
            String id = String.valueOf(treeVO.getId());
            recursiveLoopCreateSeqNum(treeVO.getChildList(), id);
        }

        // 打印结果
        System.out.println(JSON.toJSONString(treeList));
    }

    void recursiveLoopCreateSeqNum(List<TreeVO> treeList, String id) {
        if (null == treeList || treeList.size() < 1) {
            return;
        }
        for (int i = 0; i < treeList.size(); i++) {
            TreeVO treeVO = treeList.get(i);
            String childSeqNum = id + "." + (i + 1);
            treeVO.setSqNum(childSeqNum);
            recursiveLoopCreateSeqNum(treeVO.getChildList(), childSeqNum);
        }
    }

    /**
     * 创建数据
     */
    List<TreeVO> createData() {
        // 树形结构数据列表
        List<TreeVO> treeVOList = new ArrayList<>();

        // 造数据
        TreeVO treeVO1 = new TreeVO();
        treeVO1.setParentId(5L);
        treeVO1.setId(1L);

        TreeVO treeVO2 = new TreeVO();
        treeVO2.setParentId(5L);
        treeVO2.setId(2L);

        TreeVO treeVO3 = new TreeVO();
        treeVO3.setParentId(6L);
        treeVO3.setId(3L);

        TreeVO treeVO4 = new TreeVO();
        treeVO4.setParentId(6L);
        treeVO4.setId(4L);

        TreeVO treeVO7 = new TreeVO();
        treeVO7.setParentId(2L);
        treeVO7.setId(7L);

        List<TreeVO> treeVO7ChildList = new ArrayList<>();
        treeVO7ChildList.add(treeVO7);

        treeVO2.setChildList(treeVO7ChildList);

        List<TreeVO> treeVO5ChildList = new ArrayList<>();
        treeVO5ChildList.add(treeVO1);
        treeVO5ChildList.add(treeVO2);

        TreeVO treeVO5 = new TreeVO();
        treeVO5.setId(5L);
        treeVO5.setParentId(0L);
        treeVO5.setChildList(treeVO5ChildList);

        List<TreeVO> treeVO6ChildList = new ArrayList<>();
        treeVO6ChildList.add(treeVO3);
        treeVO6ChildList.add(treeVO4);

        TreeVO treeVO6 = new TreeVO();
        treeVO6.setId(6L);
        treeVO6.setParentId(0L);
        treeVO6.setChildList(treeVO6ChildList);

        treeVOList.add(treeVO5);
        treeVOList.add(treeVO6);

        return treeVOList;
    }

}