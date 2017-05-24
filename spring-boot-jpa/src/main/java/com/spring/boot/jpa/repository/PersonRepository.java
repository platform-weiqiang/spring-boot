package com.spring.boot.jpa.repository;

import com.spring.boot.jpa.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/22.
 * 总结：spring boot jpa已经实现很多的sql查询方法
 * 但在实际中。jpa提供的有限。下次介绍高级的用法
 */
public interface PersonRepository extends JpaRepository<Person,Long>,JpaSpecificationExecutor<Person>{


    @Query("select p from Person p where p.name=?1")
    List<Person> findByName(String name);

    //使用jpa自带的属性查询
    List<Person> findByNameContaining(String name);

    //使用jpa自带的属性查询
    List<Person> findFirst2ByName(String name);

    //使用jpa自带的属性查询
    List<Person> findTop20ByName(String name);

    @Query("select p from Person p where p.age=:age")
    List<Person> findByAge(@Param("age") Integer age);

    @Modifying
    @Transactional
    @Query("update Person p set p.name=?1 where p.id=?2")
    int setName(String name, Long id);

    List<Person> findAll(Sort orders);
}
