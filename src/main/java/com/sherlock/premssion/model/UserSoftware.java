package com.sherlock.premssion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * author LinJiaHe
 * date 2020/8/26
 */
@Data
@Entity
public class UserSoftware {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     *
     */
    private Integer userId;

    /**
     * 名称
     */
    private String userName;

    /**
     *
     */
    private Integer softwareId;

    /**
     * 名称
     */
    private String softwareName;

    /**
     * 使用类型
     */
    private String useType;


    /**
     * 备注
     */
    private String remark;

    /**
     * 价格
     */
    private BigDecimal softwarePrice;

    /**
     * 定金
     */
    private BigDecimal softwarePrice1;

    /**
     * 余款
     */
    private BigDecimal softwarePrice2;

    /**
     * 到账
     */
    private BigDecimal softwarePrice3;

    /**
     */
    private Date softwareTime;

    /**
     * 状态(0:未付款 1:已付款 2:审核通过 3:审核不通过)
     */
    private int status;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
