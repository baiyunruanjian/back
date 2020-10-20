package com.sherlock.premssion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户
 * author: 小宇宙
 * date: 2018/4/5
 */
@Data
@Entity
public class SysUser {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 是否禁用 0：否；1：是
     */
    private String forbidden;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 主营
     */
    private String zhuYing;

    private String wangZhi;

    private String beiZhu;

    private String weiXin;

    /**
     * 修改时间
     */
    @DateTimeFormat
    private Date updateTime;

    /**
     * 创建时间
     */
    @DateTimeFormat
    private Date createTime;
}
