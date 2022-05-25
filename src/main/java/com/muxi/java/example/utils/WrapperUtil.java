package com.muxi.java.example.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muxi.java.example.domain.GoodsStore;
import com.muxi.java.example.domain.SysUser;
import com.muxi.java.example.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author jjl
 * @date 2022/5/23
 */
public abstract class WrapperUtil {

    public static <T> QueryWrapper<T> entity2Wrapper(T entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.setEntity(entity);
        // 遍历属性
        for (Field field : fields) {
            Method method = null;
            try {
                String fieldName = field.getName();
                // 跳过serialVersionUID
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                // 拿到列名 DB > Table
                String column = StrUtil.toUnderlineCase(fieldName);
                // get方法
                method = clazz.getMethod("get" + captureName(fieldName));
                Object value = method.invoke(entity);
                if (value instanceof String) {
                    String str = (String) value;
                    wrapper.eq(StrUtil.isNotBlank(str), column, value);
                } else {
                    wrapper.eq(value != null, column, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return wrapper;
    }

    /**
     * 将字符串的首字母转大写
     *
     * @param str 需要转换的字符串
     * @return String
     */
    private static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("jack");
        QueryWrapper<User> wrapper = WrapperUtil.entity2Wrapper(user);
        System.out.println(wrapper);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        User u2 = new User();
        u2.setName("Lily");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", u2.getName());
        System.out.println(queryWrapper);
    }
}
