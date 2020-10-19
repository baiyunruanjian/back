package com.sherlock.premssion.controller;

import com.sherlock.premssion.enums.REnum;
import com.sherlock.premssion.exception.SystemException;
import com.sherlock.premssion.from.SysUserFrom;
import com.sherlock.premssion.model.SysUser;
import com.sherlock.premssion.service.SysUserService;
import com.sherlock.premssion.utils.Assert;
import com.sherlock.premssion.utils.RUtil;
import com.sherlock.premssion.utils.ShiroUtil;
import com.sherlock.premssion.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * author: 小宇宙
 * date: 2018/4/7
 */
@RestController
@RequestMapping("/anno")
@Slf4j
public class AnnoController {

    @Autowired
    SysUserService sysUserService;

    @PostMapping("/login")
    public R login(@RequestBody Map<String,String> map){

        Assert.isBlank(map.get("account"),"账号不能为空");
        Assert.isBlank(map.get("password"),"密码不能为空");


        try{
            Subject subject = ShiroUtil.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(map.get("account"),map.get("password"));
            subject.login(token);
        }catch (UnknownAccountException e) {
           return RUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(),REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }catch (IncorrectCredentialsException e) {
           return RUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(),REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }catch (DisabledAccountException e) {
           return RUtil.error(REnum.ACCOUNT_DISABLE.getCode(),REnum.ACCOUNT_DISABLE.getMessage());
        }catch (AuthenticationException e) {
           return RUtil.error(REnum.AUTH_ERROR.getCode(),REnum.AUTH_ERROR.getMessage());
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return RUtil.success(user);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public R register(@Valid @RequestBody SysUserFrom sysUserFrom, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增用户】参数不正确:sysUserFrom={}"+ sysUserFrom);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        sysUserFrom.setForbidden("0");
        return sysUserService.saveUser(sysUserFrom);
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public R logout(){
        ShiroUtil.logout();
        return RUtil.success();
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/notLogin")
    public R notLogin(){
       return RUtil.error(REnum.NOT_LOGIN.getCode(),REnum.NOT_LOGIN.getMessage());
    }
}
