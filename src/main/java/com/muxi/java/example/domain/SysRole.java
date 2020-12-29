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
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String iname;

    private String remark;

    private String sn;

    private Integer pid;

    private Integer nodeTypeId;


    public static final String ID = "id";

    public static final String INAME = "iname";

    public static final String REMARK = "remark";

    public static final String SN = "sn";

    public static final String PID = "pid";

    public static final String NODE_TYPE_ID = "node_type_id";

}
