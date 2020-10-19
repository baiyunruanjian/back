package com.sherlock.premssion.service;

import com.sherlock.premssion.model.UserSoftware;
import com.sherlock.premssion.vo.R;
import org.springframework.data.domain.Pageable;


/**
 * author: 小宇宙
 * date: 2018/4/5
 */
public interface OrderService {

    R save(UserSoftware userSoftware);

    R selectList(Pageable pageable);

    R selectDetail(Integer id);

    R updateStatus(UserSoftware userSoftware) throws Exception;

}
