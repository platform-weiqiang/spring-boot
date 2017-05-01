package com.springboot.security.repository;

import com.springboot.security.entity.SysUser;;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<SysUser,Integer>{

    SysUser findByName(String userName);

}
