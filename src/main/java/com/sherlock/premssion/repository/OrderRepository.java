package com.sherlock.premssion.repository;

import com.sherlock.premssion.model.UserSoftware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * author: 小宇宙
 * date: 2018/4/5
 */
public interface OrderRepository extends JpaRepository<UserSoftware,Integer> {


    Page<UserSoftware> findAll(Specification<UserSoftware> sysRoleSpecification, Pageable pageable);
}
