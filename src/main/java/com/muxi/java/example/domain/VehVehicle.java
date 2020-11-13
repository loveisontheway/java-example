package com.muxi.java.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 车辆主表【veh_vehicle】
 * </p>
 *
 * @author muxi
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("veh_vehicle")
public class VehVehicle implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 车辆id
     */
    private Integer vehicleId;

    /**
     * 车牌号
     */
    private String licensePlateNo;

    /**
     * 所属企业，外键：ent_enterprise
     */
    private Integer enterpriseId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态，1:有效，0:无效
     */
    private Integer state;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间，格式：YYYY-MM-DD hh:mm:ss
     */
    private LocalDateTime creatorTime;

    /**
     * 修改者
     */
    private String mender;

    /**
     * 修改时间
     */
    private LocalDateTime menderTime;


    public static final String VEHICLE_ID = "vehicle_id";

    public static final String LICENSE_PLATE_NO = "license_plate_no";

    public static final String ENTERPRISE_ID = "enterprise_id";

    public static final String SORT = "sort";

    public static final String REMARK = "remark";

    public static final String STATE = "state";

    public static final String CREATOR = "creator";

    public static final String CREATOR_TIME = "creator_time";

    public static final String MENDER = "mender";

    public static final String MENDER_TIME = "mender_time";

}
