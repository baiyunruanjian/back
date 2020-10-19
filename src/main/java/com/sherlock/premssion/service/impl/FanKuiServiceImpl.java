package com.sherlock.premssion.service.impl;

import com.sherlock.premssion.model.Fankui;
import com.sherlock.premssion.model.Fankui;
import com.sherlock.premssion.repository.FanKuiRepository;
import com.sherlock.premssion.repository.MouldRepository;
import com.sherlock.premssion.service.FanKuiService;
import com.sherlock.premssion.service.MouldService;
import com.sherlock.premssion.utils.RUtil;
import com.sherlock.premssion.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: 小宇宙
 * date: 2018/4/5
 */
@Service
@Transactional
@Slf4j
public class FanKuiServiceImpl implements FanKuiService {

    @Autowired
    FanKuiRepository fanKuiRepository;


    @Override
    public R save(Fankui fankui) {
        fankui.setUpdateTime(new Date());
        fankui.setCreateTime(new Date());
        fanKuiRepository.save(fankui);
        return RUtil.success();
    }

    @Override
    public R selectList(Pageable pageable) {
        Specification<Fankui> specification = new Specification<Fankui>() {
            @Override
            public Predicate toPredicate(Root<Fankui> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return RUtil.success(fanKuiRepository.findAll(specification,pageable));
    }

    @Override
    public R selectDetail(Integer id) {
        return RUtil.success(fanKuiRepository.findOne(id));
    }

    @Override
    public R update(Fankui mould) {
        mould.setUpdateTime(new Date());
        Fankui softwareSave = fanKuiRepository.save(mould);
        log.info("软件表修改：softwareSave = {}"+softwareSave);
        return RUtil.success();
    }
}
