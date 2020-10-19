package com.sherlock.premssion.service;

import com.sherlock.premssion.model.Mould;
import com.sherlock.premssion.vo.R;
import org.springframework.data.domain.Pageable;


/**
 * author: 小宇宙
 * date: 2018/4/5
 */
public interface MouldService {

    R save(Mould Mould);

    R selectList(Pageable pageable);

    R selectDetail(Integer id);

    R update(Mould Mould);

}
