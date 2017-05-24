package com.spring.boot.jpa.service;

import com.spring.boot.jpa.entity.Person;

/**
 * 用来测试事物的回滚操作
 */
public interface PersonService {

    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithNoRollBack(Person person);
}
