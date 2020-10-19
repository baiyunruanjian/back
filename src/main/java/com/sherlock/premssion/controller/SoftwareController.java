package com.sherlock.premssion.controller;

import com.sherlock.premssion.enums.REnum;
import com.sherlock.premssion.exception.SystemException;
import com.sherlock.premssion.from.SysUserFrom;
import com.sherlock.premssion.model.SysUser;
import com.sherlock.premssion.model.UserSoftware;
import com.sherlock.premssion.model.Software;
import com.sherlock.premssion.service.OrderService;
import com.sherlock.premssion.service.SoftwareService;
import com.sherlock.premssion.service.SysUserService;
import com.sherlock.premssion.utils.Assert;
import com.sherlock.premssion.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author: 小宇宙
 * date: 2018/4/5
 */
@RestController
@RequestMapping("/software")
@Slf4j
public class SoftwareController {

    @Autowired
    SoftwareService softwareService;

    @Autowired
    OrderService orderService;

    @Autowired
    SysUserService sysUserService;

    /**
     * 新增用户
     * @param sysUserFrom
     * @param bindingResult
     * @return
     */
//    @RequiresPermissions("sys:user:insert")
    @PostMapping("/saveUser")
    public R saveUser(@Valid @RequestBody SysUserFrom sysUserFrom,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增用户】参数不正确:sysUserFrom={}"+ sysUserFrom);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        return sysUserService.saveUser(sysUserFrom);
    }

    /**
     * 新增软件
     * @param software
     * @return
     */
//    @RequiresPermissions("sys:software:insert")
    @PostMapping("/save")
    public R save(@Valid @RequestBody Software software){

        return softwareService.save(software);
    }


    /**
     * 查询软件列表
     * @param page
     * @param size
     * @return
     */
//    @RequiresPermissions("sys:software:list")
    @GetMapping("/selectList")
    public R selectList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size){

        PageRequest pageRequest = new PageRequest(page,size);
        return softwareService.selectList(pageRequest);
    }

    /**
     * 查询软件详情
     * @param id
     * @return
     */
//    @RequiresPermissions("sys:software:detail")
    @GetMapping("/selectDetail")
    public R selectDetail(@RequestParam(value = "id",required = false) Integer id){

        Assert.isNull(id,"id不能为空");
        return softwareService.selectDetail(id);
    }

    /**
     * 更新软件
     * @param software
     * @param bindingResult
     * @return
     */
//    @RequiresPermissions("sys:software:update")
    @PutMapping("/update")
    public R update(@Valid @RequestBody Software software,
                        BindingResult bindingResult){

        Assert.isNull(software.getId(),"id不能为空");

        return softwareService.update(software);
    }

    /**
     * 创建订单
     * @param userSoftware
     * @return
     */
//    @RequiresPermissions("sys:order:insert")
    @PostMapping("/orderSave")
    public R orderSave(@Valid @RequestBody UserSoftware userSoftware){

        return orderService.save(userSoftware);
    }

    /**
     * 审核
     * @param userSoftware
     * @return
     */
//    @RequiresPermissions("sys:order:updateStatus")
    @PostMapping("/updateOrderStatus")
    public R updateOrderStatus(@Valid @RequestBody UserSoftware userSoftware) throws Exception{

        Assert.isNull(userSoftware.getId(),"id不能为空");
        Assert.isNull(userSoftware.getStatus(),"status不能为空");
        return orderService.updateStatus(userSoftware);
    }

    /**
     * 查询订单列表
     * @param page
     * @param size
     * @return
     */
//    @RequiresPermissions("sys:order:list")
    @GetMapping("/selectOrderList")
    public R selectOrderList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size){

        PageRequest pageRequest = new PageRequest(page,size);
        return orderService.selectList(pageRequest);
    }

    /**
     * 查订单详情
     * @param id
     * @return
     */
//    @RequiresPermissions("sys:order:detail")
    @GetMapping("/selectOrderDetail")
    public R selectOrderDetail(@RequestParam(value = "id",required = false) Integer id){

        Assert.isNull(id,"id不能为空");
        return orderService.selectDetail(id);
    }

}
