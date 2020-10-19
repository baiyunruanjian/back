package com.sherlock.premssion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 * author: 小宇宙
 * date: 2018/4/5
 */
@Data
@Entity
public class Mould {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 二维码
     */
    private String mouldName;

    /**
     * 价格
     */
    private String factoryName;

    /**
     * 链接
     */
    private String phone;

    /**
     * 链接
     */
    private String status;

    /**
     * 过期时间
     */
    private String jiemoPeople;

    /**
     * 过期时间
     */
        private String jiemoPhone;

    /**
     * 过期时间
     */
    private String jiaomoOrder;

    /**
     * 过期时间
     */
    private String remark;

    /**
     * 修改时间
     */
    private Date jiaomoTime;

    /**
     * 创建时间
     */
    private Date jiemoTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
