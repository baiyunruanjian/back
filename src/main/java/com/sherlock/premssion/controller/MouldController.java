package com.sherlock.premssion.controller;

import com.sherlock.premssion.model.Mould;
import com.sherlock.premssion.model.UserSoftware;
import com.sherlock.premssion.service.OrderService;
import com.sherlock.premssion.service.MouldService;
import com.sherlock.premssion.utils.Assert;
import com.sherlock.premssion.vo.R;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/mould")
@Slf4j
public class MouldController {

    @Autowired
    MouldService mouldService;

    /**
     * 新增软件
     * @param mould
     * @return
     */
//    @RequiresPermissions("sys:mould:insert")
    @PostMapping("/save")
    public R save(@Valid @RequestBody Mould mould){

        return mouldService.save(mould);
    }


    /**
     * 查询软件列表
     * @param page
     * @param size
     * @return
     */
//    @RequiresPermissions("sys:mould:list")
    @GetMapping("/selectList")
    public R selectList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size){

        PageRequest pageRequest = new PageRequest(page,size);
        return mouldService.selectList(pageRequest);
    }

    /**
     * 查询软件详情
     * @param id
     * @return
     */
//    @RequiresPermissions("sys:mould:detail")
    @GetMapping("/selectDetail")
    public R selectDetail(@RequestParam(value = "id",required = false) Integer id){

        Assert.isNull(id,"id不能为空");
        return mouldService.selectDetail(id);
    }

    /**
     * 更新软件
     * @param mould
     * @param bindingResult
     * @return
     */
//    @RequiresPermissions("sys:mould:update")
    @PutMapping("/update")
    public R update(@Valid @RequestBody Mould mould,
                        BindingResult bindingResult){

        Assert.isNull(mould.getId(),"id不能为空");

        return mouldService.update(mould);
    }

}
