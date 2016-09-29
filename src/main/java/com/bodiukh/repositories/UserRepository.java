package com.bodiukh.repositories;


import com.bodiukh.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
}
