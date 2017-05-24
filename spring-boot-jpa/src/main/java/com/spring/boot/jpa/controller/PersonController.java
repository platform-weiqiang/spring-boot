package com.spring.boot.jpa.controller;

import com.spring.boot.jpa.entity.Person;
import com.spring.boot.jpa.repository.PersonRepository;
import com.spring.boot.jpa.service.PersonService;
import com.spring.boot.jpa.specification.PersonSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    //查询所有的数据
    @RequestMapping("/person")
    public List<Person> getPersonList(){
        List<Person> personList=personRepository.findAll();
        return personList;
    }

    //根据id查询相关的数据
    @RequestMapping("/person/{id}")
    public Person getPersonOne(@PathVariable("id")Long id){
        Person person=personRepository.findOne(id);
        return person;
    }

    //根据id删除相关的数据
    @RequestMapping("/person/delete/{id}")
    public void deletePersonOne(@PathVariable("id")Long id){
        personRepository.delete(id);
    }

    //根据名字name查询相关的数据
    @RequestMapping("/person/findByName/{name}")
    public List<Person> findByName(@PathVariable("name")String name){
        List<Person> list=personRepository.findByName(name);
        return list;
    }

    //根据名字模糊查询相关的数据
    @RequestMapping("/person/findByNameLike/{name}")
    public List<Person> findByNameLike(@PathVariable("name")String name){
        List<Person> list=personRepository.findByNameContaining(name);
        return list;
    }

    //查询符合提交的前2条数据
    @RequestMapping("/person/findFirst2ByName/{name}")
    public List<Person> findFirst2ByName(@PathVariable("name")String name){
       List<Person> list= personRepository.findFirst2ByName(name);
       return list;
    }

    //查询符合提交的前20条数据
    @RequestMapping("/person/findTop20ByName/{name}")
    public List<Person> findTop20ByName(@PathVariable("name")String name){
        List<Person> list=personRepository.findTop20ByName(name);
        return list;
    }

    //jpa更新语句的用法和定义
    @RequestMapping("/person/update/{name}/{id}")
    public Integer update(@PathVariable("name")String name,@PathVariable("id")Long id){
        int i=personRepository.setName(name,id);
        return i;
    }

    //用名称来匹配查询的参数
    @RequestMapping("/person/findAge/{age}")
    public List<Person> findAgeList(@PathVariable("age")Integer age){
        return personRepository.findByAge(age);
    }

    @RequestMapping("/person/save")
    public void savePerson(){
        Person person=new Person();
        person.setAge(32);
        person.setName("刘胜利");
        person.setAddress("北京市朝阳区");
        person.setCreate(new Date());
        person.setUpdate(new Date());
        person.setTel("15955556365");
        personRepository.save(person);
    }

    @RequestMapping("/person/batchInsert")
    public void batchInsertPerson(){
        List<Person> list=new ArrayList<>();
        Person person0=new Person();
        person0.setAge(22);
        person0.setName("张梅娟");
        person0.setAddress("北京市海淀区");
        person0.setCreate(new Date());
        person0.setUpdate(new Date());
        person0.setTel("15923138888");
        Person person1=new Person();
        person1.setAge(16);
        person1.setName("杭佳佳");
        person1.setAddress("西安市雁塔区");
        person1.setCreate(new Date());
        person1.setUpdate(new Date());
        person1.setTel("15932321111");
        Person person2=new Person();
        person2.setAge(32);
        person2.setName("张丽丽");
        person2.setAddress("北京市朝阳区");
        person2.setCreate(new Date());
        person2.setUpdate(new Date());
        person2.setTel("15998745325");
        list.add(person0);
        list.add(person1);
        list.add(person2);
        personRepository.save(list);
    }


    //Specification构造查询条件
    @RequestMapping("/person/Specification/{name}")
    public List<Person> getSpecificationByName(@PathVariable("name")String name){
        List<Person> list=personRepository.findAll(PersonSpecification.personFromByName(name));
        return list;
    }

    //jpa排序
    @RequestMapping("/person/sort")
    public List<Person> getPersonSort(){
        List<Person> list=personRepository.findAll(new Sort(Sort.Direction.ASC,"age"));
        return list;
    }

    //jpa分页
    @RequestMapping("/person/page")
    public Page<Person> getPageList(){
        Page<Person> page=personRepository.findAll(new PageRequest(0,10));
        return page;
    }

    //整合排序加分页
    @RequestMapping("/person/sortandpage")
    public Page<Person> getSortAndpAge(){
        Sort sort=new Sort(Sort.Direction.DESC,"age","create");
        Pageable pageable=new PageRequest(0,10,sort);
        return personRepository.findAll(pageable);
    }

    @RequestMapping("/savePersonWithRollBack")
    public Person savePersonWithRollBack(){
        Person person=new Person();
        person.setAge(33);
        person.setName("刘胜利");
        person.setAddress("北京市朝阳区");
        person.setCreate(new Date());
        person.setUpdate(new Date());
        person.setTel("15955556665");
        Person p= personService.savePersonWithRollBack(person);
        return p;
    }

    @RequestMapping("/savePersonWithNoRollBack")
    public Person savePersonWithNoRollBack(){
        Person person=new Person();
        person.setAge(35);
        person.setName("刘胜利");
        person.setAddress("北京市密云区");
        person.setCreate(new Date());
        person.setUpdate(new Date());
        person.setTel("15955558888");
        Person p= personService.savePersonWithNoRollBack(person);
        return p;
    }
}
