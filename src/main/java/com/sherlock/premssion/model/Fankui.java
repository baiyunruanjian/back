package com.sherlock.premssion.model;

import lombok.Data;

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
public class Fankui {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    private String content;

    private String status;


    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
