package com.sherlock.premssion.repository;

import com.sherlock.premssion.model.Mould;
import com.sherlock.premssion.model.Mould;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * author: 小宇宙
 * date: 2018/4/5
 */
public interface MouldRepository extends JpaRepository<Mould,Integer> {


    Page<Mould> findAll(Specification<Mould> sysRoleSpecification, Pageable pageable);
}
