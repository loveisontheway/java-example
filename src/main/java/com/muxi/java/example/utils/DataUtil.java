package com.muxi.java.example.utils;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 数据处理工具类
 *
 * @author jjl
 * @date 2021/2/26
 */
@Slf4j
public class DataUtil {

    /**
     * list转树状
     *
     * @param list
     * @param getPidFunction 获取父id的方法
     * @param getIdFunction  获取id的方法
     * @param setSon         设置子集合的方法
     * @param heard          父节点id
     * @return java.util.ListVo<T> 返回最上级节点列表
     */
    public static <T, K> List<T> list2Tree(
            Collection<T> list,
            Function<T, K> getPidFunction,
            Function<T, K> getIdFunction,
            BiConsumer<T, List<T>> setSon,
            K heard) {
        List<T> ts = new ArrayList<>();
        for (T t : list) {
            boolean flag;
            K pid = getPidFunction.apply(t);
            if (heard == null) {
                flag = pid == null;
            } else {
                flag = pid != null && pid.equals(heard);
            }
            if (flag) {
                findSon(list, t, getIdFunction, getPidFunction, setSon);
                ts.add(t);
            }
        }
        return ts;
    }

    /**
     * list遍历
     *
     * @param ts       要遍历的集合
     * @param consumer 遍历时要执行的方法
     */
    public static <T> void listForeach(List<T> ts, Consumer<T> consumer) {
        ts.forEach(consumer);
    }

    /**
     * 判断当前数以及其子节点 是否满足筛选条件
     *
     * @param t         树
     * @param predicate 筛选条件
     * @param getSon    获取子集合的方法
     * @param setSon    设置子集合的方法
     * @return boolean
     */
    public static <T> boolean treeNodeFilter(
            T t, Predicate<T> predicate, Function<T, List<T>> getSon, BiConsumer<T, List<T>> setSon) {
        if (predicate.test(t)) {
            log.info("检测通过");
            return true;
        } else {
            List<T> sonTrees = getSon.apply(t);
            log.info("检测不通过 检测子节点");
            if (!isEmpty(sonTrees)) {
                List<T> tmpSonTree = new ArrayList<>();
                boolean flag = false;
                for (T son : sonTrees) {
                    if (treeNodeFilter(son, predicate, getSon, setSon)) {
                        flag = true;
                        tmpSonTree.add(son);
                    }
                }
                log.info("子节点检测结果：{}", flag);
                setSon.accept(t, tmpSonTree);
                return flag;
            } else {
                log.info("没有子节点");
                return false;
            }
        }
    }

    /**
     * 根据所有结果集list查找t的所有后代,并已树状返回t对象
     *
     * @param list              所有结果集
     * @param t                 要查询的结果集
     * @param getIdFunction     获取id的方法
     * @param getParentFunction 获取父id的方法
     * @param setSon            设置子集合的方法
     */
    public static <T, K> void findSon(
            Collection<T> list,
            T t,
            Function<T, K> getIdFunction,
            Function<T, K> getParentFunction,
            BiConsumer<T, List<T>> setSon) {
        K k = getIdFunction.apply(t);
        List<T> sonList =
                listTypeFilter(
                        list, p -> getParentFunction.apply(p) != null && getParentFunction.apply(p).equals(k));
        sonList.forEach(p -> findSon(list, p, getIdFunction, getParentFunction, setSon));
        setSon.accept(t, sonList);
    }

    /**
     * 所有获取后代
     *
     * @author liting
     */
    public static <T, K> List<T> getChildList(
            Collection<T> list, Function<T, K> getPidFunction, Function<T, K> getIdFunction, K id) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (getPidFunction.apply(t).equals(id)) {
                result.addAll(getChildList(list, getPidFunction, getIdFunction, getIdFunction.apply(t)));
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 集合类型转换
     *
     * @param ts       要被转换的
     * @param function 转换方法
     * @return java.util.List<K>
     */
    public static <T, K> List<K> listTypeChange(Collection<T> ts, Function<T, K> function) {
        if (ts != null && ts.size() > 0) {
            return ts.parallelStream().map(function).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 集合类型先过滤，再转换
     *
     * @param ts
     * @param biPredicate
     * @param function
     * @return java.util.List<K>
     * @date 2021/6/18 13:48
     */
    public static <T, K> List<K> listTypeChange(
            Collection<T> ts, Predicate<T> biPredicate, Function<T, K> function) {
        if (ts != null && ts.size() > 0) {
            return ts.parallelStream().filter(biPredicate).map(function).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param list
     * @return java.lang.Boolean
     * @date 2021/6/18 13:49
     */
    public static boolean isEmpty(Collection list) {
        return list == null || list.size() == 0;
    }

    /**
     * 集合类型转换并返回set
     *
     * @param ts
     * @param function
     * @return java.util.Set<K>
     * @date 2021/6/18 13:50
     */
    public static <T, K> Set<K> listTypeChangeAnd2Set(Collection<T> ts, Function<T, K> function) {
        if (ts != null && ts.size() > 0) {
            return ts.parallelStream().map(function).collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }
    }

    /**
     * 集合过滤
     *
     * @param ts          集合
     * @param biPredicate 过滤方法
     * @return java.util.List<T>
     * @date 2021/6/18 13:51
     */
    public static <T> List<T> listTypeFilter(Collection<T> ts, Predicate<T> biPredicate) {
        if (ts != null && ts.size() > 0) {
            return ts.parallelStream().filter(biPredicate).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 集合转map 前提是key 也就是function的返回值不能为空
     *
     * @param ts       集合
     * @param function 获取key的方法
     * @return java.util.Map<K, T>
     * @date 2021/6/18 13:52
     */
    public static <T, K> Map<K, T> listToMap(Collection<T> ts, Function<T, K> function) {
        return ts.parallelStream().collect(Collectors.toMap(function, t -> t));
    }

    /**
     * 集合分组 前提是key 也就是function的返回值不能为空,不能重复
     *
     * @param ts       集合
     * @param function 获取key的方法
     * @return java.util.Map<K, T>
     * @date 2021/6/18 13:52
     */
    public static <T, K> Map<K, List<T>> listGroup(Collection<T> ts, Function<T, K> function) {
        return ts.parallelStream().collect(Collectors.groupingBy(function));
    }

    /**
     * 集合分组并统计 前提是key 也就是function的返回值不能为空,不能重复
     *
     * @param ts
     * @param function
     * @return java.util.Map<K, java.lang.Long>
     * @date 2021/6/18 13:54
     */
    public static <T, K> Map<K, Long> listGroupCount(List<T> ts, Function<T, K> function) {
        return ts.parallelStream().collect(Collectors.groupingBy(function, Collectors.counting()));
    }

    /**
     * list去重
     *
     * @param ts
     * @param keyExtractor
     * @return java.util.List<T>
     * @date 2021/6/18 13:58
     */
    public static <T, U extends Comparable<? super U>> List<T> distinct(
            Collection<T> ts, Function<? super T, ? extends U> keyExtractor) {
        return ts.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<T>(Comparator.comparing(keyExtractor))),
                                ArrayList::new));
    }

    public static boolean emptyCollection(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 遍历一颗树的所有路径 如 A B C D E F 的路径集合为为 [[A,B],[A,C],[A,D,E],[A,D,F]]
     *
     * @param t          树
     * @param lists      路径集合
     * @param nodes      当前以及存在的路径
     * @param getSonFunc 获取子集合方法
     * @param setSon     设置子集合方法
     * @return java.util.List<java.util.List < T>>
     * @date 2021/6/18 13:59
     */
    public static <T> List<List<T>> getAllPath(
            T t,
            List<List<T>> lists,
            List<T> nodes,
            Function<T, List<T>> getSonFunc,
            BiConsumer<T, List<T>> setSon) {
        List<T> childNodes = new ArrayList<>();
        if (getSonFunc.apply(t) != null) {
            childNodes = Lists.newCopyOnWriteArrayList(getSonFunc.apply(t));
        }
        setSon.accept(t, null);
        nodes.add(t);
        if (DataUtil.isEmpty(childNodes)) {
            lists.add(nodes);
        } else {
            for (T son : childNodes) {
                List<T> copyNodes = Lists.newCopyOnWriteArrayList(nodes);
                getAllPath(son, lists, copyNodes, getSonFunc, setSon);
            }
        }
        return lists;
    }

    /**
     * @param t          树
     * @param getSonFunc 获取子集合方法
     * @param setSon     设置子集合方法
     * @return java.util.List<T>
     * @date 2021/6/18 14:03
     */
    public static <T> List<T> tree2List(
            T t, Function<T, List<T>> getSonFunc, BiConsumer<T, List<T>> setSon) {
        List<T> list = new ArrayList<>();
        List<T> sons = new ArrayList<>();
        if (getSonFunc.apply(t) != null) {
            sons = Lists.newCopyOnWriteArrayList(getSonFunc.apply(t));
        }
        setSon.accept(t, null);
        list.add(t);
        if (!isEmpty(sons)) {
            for (T son : sons) {
                list.addAll(tree2List(son, getSonFunc, setSon));
            }
        }
        return list;
    }

    /**
     * 树集合过滤
     *
     * @param trees      树集合
     * @param pi         过滤条件
     * @param getSonFunc 树对象获取子集合方法
     * @param setSon     设置子集合方法
     * @return java.util.List<T>
     * @date 2021/6/18 14:03
     */
    public static <T> List<T> treeListFilter(
            List<T> trees,
            Predicate<T> pi,
            Function<T, List<T>> getSonFunc,
            BiConsumer<T, List<T>> setSon) {
        // 过滤
        trees =
                listTypeFilter(
                        trees,
                        ts ->
                                treeNodeFilter(
                                        ts,
                                        // 过滤条件
                                        pi,
                                        getSonFunc,
                                        setSon));
        return trees;
    }
}

