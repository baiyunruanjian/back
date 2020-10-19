package com.sherlock.premssion.service;

import com.sherlock.premssion.model.Software;
import com.sherlock.premssion.vo.R;
import org.springframework.data.domain.Pageable;


/**
 * author: 小宇宙
 * date: 2018/4/5
 */
public interface SoftwareService {

    R save(Software software);

    R selectList(Pageable pageable);

    R selectDetail(Integer id);

    R update(Software software);

}
