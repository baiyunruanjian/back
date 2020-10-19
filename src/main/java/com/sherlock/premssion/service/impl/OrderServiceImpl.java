package com.sherlock.premssion.service.impl;

import com.sherlock.premssion.model.UserSoftware;
import com.sherlock.premssion.model.Software;
import com.sherlock.premssion.repository.OrderRepository;
import com.sherlock.premssion.repository.SoftwareRepository;
import com.sherlock.premssion.service.OrderService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author: 小宇宙
 * date: 2018/4/5
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SoftwareRepository softwareRepository;

    @Override
    public R save(UserSoftware userSoftware) {
        userSoftware.setStatus(0);
        userSoftware.setUpdateTime(new Date());
        userSoftware.setCreateTime(new Date());
        orderRepository.save(userSoftware);
        return RUtil.success();
    }

    @Override
    public R selectList(Pageable pageable) {
        Specification<UserSoftware> specification = new Specification<UserSoftware>() {
            @Override
            public Predicate toPredicate(Root<UserSoftware> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<>();
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
        return RUtil.success(orderRepository.findAll(specification,pageable));
    }

    @Override
    public R selectDetail(Integer id) {
        return RUtil.success(orderRepository.findOne(id));
    }

    @Override
    public R updateStatus(UserSoftware userSoftware) throws Exception{
        UserSoftware findUserSoftware = orderRepository.findOne(userSoftware.getId());

        //如果审核通过设置过期时间
        if(userSoftware.getStatus()==2){
            Software software = softwareRepository.findOne(findUserSoftware.getSoftwareId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = plusDay2(software.getEffectiveTime());
            Date date = simpleDateFormat.parse(dateStr);

            findUserSoftware.setSoftwareTime(date);
        }
        findUserSoftware.setStatus(userSoftware.getStatus());
        findUserSoftware.setUpdateTime(new Date());
        orderRepository.save(findUserSoftware);
        return RUtil.success();
    }


    /**
     * 当前日期加上天数后的日期
     * @param num 为增加的天数
     * @return
     */
    public static String plusDay2(int num){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        System.out.println("现在的日期是：" + currdate);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }

}
