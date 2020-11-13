package com.muxi.java.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author muxi
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String iname;

    private Integer orgId;

    private String password;

    private Integer roleId;

    private String sn;

    private Integer state;


    public static final String ID = "id";

    public static final String INAME = "iname";

    public static final String ORG_ID = "org_id";

    public static final String PASSWORD = "password";

    public static final String ROLE_ID = "role_id";

    public static final String SN = "sn";

    public static final String STATE = "state";

}
