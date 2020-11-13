package com.muxi.java.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author muxi
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_store")
public class GoodsStore implements Serializable {

    private static final long serialVersionUID=1L;

    private String code;

    private Integer store;

    private LocalDateTime updateTime;


    public static final String CODE = "code";

    public static final String STORE = "store";

    public static final String UPDATE_TIME = "update_time";

}
