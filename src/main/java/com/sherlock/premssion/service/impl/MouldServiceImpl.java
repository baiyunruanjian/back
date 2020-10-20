package com.sherlock.premssion.service.impl;

import com.sherlock.premssion.model.Mould;
import com.sherlock.premssion.repository.MouldRepository;
import com.sherlock.premssion.repository.SoftwareRepository;
import com.sherlock.premssion.service.MouldService;
import com.sherlock.premssion.service.SoftwareService;
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
public class MouldServiceImpl implements MouldService {

    @Autowired
    MouldRepository mouldRepository;


    @Override
    public R save(Mould mould) {

        mould.setUpdateTime(new Date());
        mould.setCreateTime(new Date());
        Mould softwareSave = mouldRepository.save(mould);
        log.info("软件表保存：softwareSave = {}"+softwareSave);
        return RUtil.success();
    }

    @Override
    public R selectList(Pageable pageable) {
        Specification<Mould> specification = new Specification<Mould>() {
            @Override
            public Predicate toPredicate(Root<Mould> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return RUtil.success(mouldRepository.findAll(specification,pageable));
    }

    @Override
    public R selectDetail(Integer id) {
        return RUtil.success(mouldRepository.findOne(id));
    }

    @Override
    public R update(Mould mould) {
//        mould.setUpdateTime(new Date());
        Mould softwareSave = mouldRepository.save(mould);
        log.info("软件表修改：softwareSave = {}"+softwareSave);
        return RUtil.success();
    }

    @Override
    public R delete(Integer id) {
        mouldRepository.delete(id);
        return RUtil.success();
    }
}
