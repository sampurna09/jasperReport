package com.jasper.repository;


import org.springframework.data.repository.CrudRepository;

import com.jasper.domain.User;




public interface UserRepository extends CrudRepository<User, Long>{

}
