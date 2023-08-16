package com.muxi.java.example.singleton;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author jjl
 * @date 2023/8/16
 */
@Component
public class GlobalObject {
    private static final GlobalObject instance = new GlobalObject();

    @Resettable
    private String name;
    @Resettable
    private Integer age;

    @Resettable
    private boolean bool;

    @Resettable
    private BigDecimal price;

    private String addr;

    private GlobalObject() {
    }

    public static GlobalObject getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void clearValues() {
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Resettable.class)) {
                try {
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    if (fieldType == String.class) {
                        field.set(this, "");
                    } else if (fieldType == int.class || fieldType == Integer.class) {
                        field.set(this, 0);
                    } else if (fieldType == BigDecimal.class) {
                        field.set(this, BigDecimal.ZERO);
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        field.set(this, false);
                    }
                } catch (IllegalAccessException e) {

                }
            }
        }
    }

    @Override
    public String toString() {
        return "GlobalObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bool=" + bool +
                ", price=" + price +
                ", addr='" + addr + '\'' +
                '}';
    }
}
