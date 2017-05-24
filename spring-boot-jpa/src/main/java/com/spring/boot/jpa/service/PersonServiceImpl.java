package com.spring.boot.jpa.service;

import com.spring.boot.jpa.entity.Person;
import com.spring.boot.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用来测试事物的回滚操作
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    //事务回滚
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithRollBack(Person person) {
        Person p=personRepository.save(person);
        if (person.getName().equals("刘胜利")){
            throw new IllegalArgumentException("用户已存在，数据回滚！");
        }
        return p;
    }

    //事务不回滚
    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithNoRollBack(Person person) {
        Person p=personRepository.save(person);
        if (person.getName().equals("刘胜利")){
            throw new IllegalArgumentException("用户已存在，数据不回滚！");
        }
        return p;
    }
}
